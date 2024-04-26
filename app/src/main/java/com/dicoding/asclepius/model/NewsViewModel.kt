import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.data.response.NewsResponse
import com.dicoding.asclepius.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _listNews = MutableLiveData<List<ArticlesItem>>()
    val listNews: LiveData<List<ArticlesItem>> = _listNews


    private val TAG = "NewsViewModel"

    init {
        getAllNews()
    }

    fun getAllNews() {
        val client = ApiConfig.getApiService().getNews("cancer", "health", "en", "06c1948651b04a429d3da6e5d4f721c3")
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>, response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    _listNews.value = response.body()?.articles
                } else {
                }
            }

            override fun onFailure(
                call: Call<NewsResponse>, t: Throwable
            ) {
            }
        })
    }

    fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
        if (response.isSuccessful) {
            _listNews.value = response.body()?.articles
            Log.d(TAG, "Data retrieved successfully: ${response.body()?.articles}")
        } else {
            Log.e(TAG, "Failed to retrieve data: ${response.message()}")
        }
    }

}
