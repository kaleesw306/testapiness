

data class Product (
    val name: String? = null,
    val description: String? = null,
     val availableLanguages: List<String>? = null,
     val sampleReports: Map<String, String>? = null,
     val pages:Int = 0,
     val pagesintext: String? = null,
     val report_type: String? = null,
     val authentic: String? = null,
     val remedies: String? = null,
     val vedic: String? = null,
    val price:Int = 0,
     val discount:Int = 0,
     val appDiscount:Int = 0,
     val couponDiscount:Int = 0,
    val imagePath: Map<String, String>? = null,
     val soldcount: String? = null,
     val avg:Double = 0.0,
)



class ProductsResponse {
    val products: Map<String, Product>? = null
}