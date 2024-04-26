import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.databinding.ItemNewsBinding

class NewsAdapter : ListAdapter<ArticlesItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    class MyViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticlesItem) {
            binding.tvNameNews.text = item.title
            binding.newsDescription.text = item.description
            binding.newsAuthor.text = item.author
            binding.newsPublished.text = item.publishedAt
            Glide.with(binding.root)
                .load(item.urlToImage)
                .into(binding.ivImageNews)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticlesItem)
    }

    companion object {
        private const val DATA = "data_detail"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
