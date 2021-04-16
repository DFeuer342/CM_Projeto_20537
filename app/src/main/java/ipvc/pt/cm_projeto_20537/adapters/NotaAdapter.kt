package ipvc.pt.cm_projeto_20537.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.pt.cm_projeto_20537.R
import ipvc.pt.cm_projeto_20537.ententies.Nota

class NotaAdapter internal constructor(
    context: Context) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Nota>()

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NotaViewHolder: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = notas[position]
        holder.NotaViewHolder.text = current.titulo
    }

    internal fun  setNotas(notas: List<Nota>){
        this.notas = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notas.size
}
