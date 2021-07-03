package ipvc.pt.cm_projeto_20537.api

data class Problema(
    val id: Int,
    val latitude: Float,
    val longitude: Float,
    val titulo: String,
    val descricao: String,
    val user_id: Int
)