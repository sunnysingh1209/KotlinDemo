package com.example.kotlindemo.viewmodels

import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.kotlindemo.auth.network.APIClient
import com.example.kotlindemo.auth.network.ApiInterface
import com.example.kotlindemo.auth.network.MyClass
import com.example.kotlindemo.model.Catergory
import com.example.kotlindemo.model.Country
import retrofit2.Call
import retrofit2.Callback

class CategoryViewModel : ViewModel {

    var arrayList = ArrayList<CategoryViewModel>()
    var mutableCategoryViewModel = MutableLiveData<ArrayList<CategoryViewModel>>()

    var id: String = ""
    var title: String = ""
    var des: String = ""
    var imagePath: String = ""
    var currency: String = ""
    var chBox: Int = 0

    constructor() : super() {
    }

    constructor(categoryModel: Catergory) : super() {
        this.id = categoryModel.id
        this.title = categoryModel.title
        this.des = categoryModel.des
        this.imagePath = categoryModel.imagePath
        this.currency = categoryModel.currency
        this.chBox = categoryModel.chBox
    }

    companion object {
        @JvmStatic
//        @BindingAdapter("android:src")
        @BindingAdapter("imagePath")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.getContext())
                .load(imageUrl)
                .into(view)
        }
    }

    fun getCategoryList(): LiveData<ArrayList<CategoryViewModel>> {
        arrayList.add(
            CategoryViewModel(
                Catergory(
                    "1",
                    "Sunny",
                    "Hi",
                    "https://api.androidhive.info/images/nature/1.jpg"
                )
            )
        )
        arrayList.add(
            CategoryViewModel(
                Catergory(
                    "1",
                    "Arun",
                    "Hello",
                    "https://api.androidhive.info/images/nature/2.jpg"
                )
            )
        )
        arrayList.add(
            CategoryViewModel(
                Catergory(
                    "1",
                    "Bhatia",
                    "Des",
                    "https://api.androidhive.info/images/nature/3.jpg"
                )
            )
        )
        arrayList.add(
            CategoryViewModel(
                Catergory(
                    "1",
                    "Ankesh",
                    "Anke",
                    "https://api.androidhive.info/images/nature/4.jpg"
                )
            )
        )

        var apiInterface = APIClient.getClient().create(ApiInterface::class.java)

        var call = apiInterface?.getCountryList()
        call?.enqueue(object : Callback<List<Country>> {

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(MyClass.getContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Country>>,
                response: retrofit2.Response<List<Country>>
            ) {
                var displayResponse = ""

                val resource = response.body()

                for (data in resource!!) {

                    arrayList.add(
                        CategoryViewModel(
                            Catergory(
                                "1",
                                "${data?.name}",
                                "${data.capital}",
                                "https://api.androidhive.info/images/nature/1.jpg",
                                "${data.currencies?.get(0)?.name}"
                            )
                        )
                    )
                }

                mutableCategoryViewModel.value = arrayList
            }

        })

        return mutableCategoryViewModel
    }

    fun getImageUrl(): String {
        return imagePath
    }

}/*
object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.getContext())
            .load(url)
            .into(view)
    }
}*/
