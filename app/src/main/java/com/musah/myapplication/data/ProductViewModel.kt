package com.musah.myapplication.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.musah.myapplication.models.Product
import com.musah.myapplication.navigation.ROUTE_VIEW_PRODUCTS

class ProductViewModel(var navController: NavHostController, var context: Context) {

    fun uploadProduct(imageUri: Uri?, name: String, quantity: String, price: String) {
        if (name.isBlank() || quantity.isBlank() || price.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (imageUri == null) {
            Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
            return
        }

        val productId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference().child("Products/$productId")

        storageRef.putFile(imageUri).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    val product = Product(name, quantity, price, productId, imageUrl)
                    val dbRef = FirebaseDatabase.getInstance().getReference().child("Products/$productId")

                    dbRef.setValue(product).addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            Toast.makeText(context, "Product uploaded successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(ROUTE_VIEW_PRODUCTS)
                        } else {
                            Toast.makeText(context, "Database Error: ${dbTask.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Storage Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateProduct(productId: String, imageUri: Uri?, name: String, quantity: String, price: String) {
        if (name.isBlank() || quantity.isBlank() || price.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val dbRef = FirebaseDatabase.getInstance().getReference().child("Products/$productId")

        if (imageUri != null) {
            val storageRef = FirebaseStorage.getInstance().getReference().child("Products/$productId")
            storageRef.putFile(imageUri).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        val product = Product(name, quantity, price, productId, imageUrl)
                        dbRef.setValue(product).addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                Toast.makeText(context, "Product updated successfully", Toast.LENGTH_SHORT).show()
                                navController.navigate(ROUTE_VIEW_PRODUCTS)
                            } else {
                                Toast.makeText(context, "Database Error: ${dbTask.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        } else {
            // Update without changing image
            dbRef.child("name").setValue(name)
            dbRef.child("quantity").setValue(quantity)
            dbRef.child("price").setValue(price).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Product updated successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_VIEW_PRODUCTS)
                } else {
                    Toast.makeText(context, "Database Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
