package com.monh.packager.ui.home.my_orders.found_order

import androidx.lifecycle.MutableLiveData
import com.monh.packager.R
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.products.FoundRequest
import com.monh.packager.data.remote.products.Product
import com.monh.packager.data.remote.products.ProductsRepository
import javax.inject.Inject
import com.monh.packager.utils.default


class FoundOrderViewModel @Inject constructor(private val productsRepository: ProductsRepository): BaseViewModel() {
    lateinit var product:Product
    var orderId:Int = 0
    val foundedQuantityLiveData:MutableLiveData<Int> = MutableLiveData<Int>().default(0)
    val errorLiveData:MutableLiveData<Int> = MutableLiveData()
    val markFoundedSuccessfully:MutableLiveData<Boolean> = MutableLiveData()

    fun markProductAsFound() {
        if (product.foundCount > 0){
            wrapBlockingOperation {
                handleResult(productsRepository.markOrderFound(
                    FoundRequest(
                        productID = product.order_item_id,
                        orderId = orderId.toString(),
                        quantity = product.foundCount
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
        if (product.quantity?.toInt()?:0 > product.foundCount){
            product.foundCount++
            foundedQuantityLiveData.postValue(product.foundCount)
        } else{
            showError(R.string.all_items_added)
        }
    }

    fun removeQuantity() {
        if (product.foundCount > 0){
            product.foundCount--
            foundedQuantityLiveData.postValue(product.foundCount)
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
