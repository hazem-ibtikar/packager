package com.monh.packager.ui.home.my_orders.found_order

import androidx.lifecycle.MutableLiveData
import com.monh.packager.R
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.orders.Order
import com.monh.packager.data.remote.products.FoundRequest
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import javax.inject.Inject

class FoundOrderViewModel @Inject constructor(private val productsRepository: ProductsRepository): BaseViewModel() {
    lateinit var product:Product
    var orderId:Int = 0
    var foundedQuantity = 0
    val foundedQuantityLiveData:MutableLiveData<Int> = MutableLiveData()
    val errorLiveData:MutableLiveData<Int> = MutableLiveData()
    val markFoundedSuccessfully:MutableLiveData<Boolean> = MutableLiveData()
    init {
        foundedQuantityLiveData.postValue(foundedQuantity)
    }
    fun markProductAsFound() {
        if (foundedQuantity > 0){
            wrapBlockingOperation {
                handleResult(productsRepository.markOrderFound(
                    FoundRequest(productID = product.id,
                        orderId = orderId.toString(), quantity = foundedQuantity
                    )
                )
                ){
                    markFoundedSuccessfully.postValue(true)
                }
            }
        }else{
            showError(R.string.add_product_first)
        }
    }

    fun addQuantity() {
        if (product.quantity?.toInt()?:0 > foundedQuantity){
            foundedQuantity++
            foundedQuantityLiveData.postValue(foundedQuantity)
        } else{
            showError(R.string.all_items_added)
        }
    }

    fun removeQuantity() {
        if (foundedQuantity > 0){
            foundedQuantity--
            foundedQuantityLiveData.postValue(foundedQuantity)
        } else{
            showError(R.string.no_more_items)
        }
    }
    private fun showError(errorResource:Int){
        errorLiveData.postValue(errorResource)
    }

    fun checkItemFromBarCode(contents: String) {
        if (contents == product.barCode){
            addQuantity()
        }else{
            showError(R.string.qr_un_valid)
        }
    }
}
