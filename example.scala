package json
import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.github.plokhotnyuk.jsoniter_scala.core._

//Case class definition to format the json
//Ads

case class header (header1:String, header2:String)
case class adContent (header:header, description:String)
case class ads(adUUID:String, adId:Long, content:adContent, URL:String, date: String, active:Boolean )
object example {
  def main(args: Array[String]): Unit = {

    val listas = List(List("8e511549-b62e-4fcd-ba87-8cd92fa86ddd",295494628947L,"Headline primero","Headline segundo","Descripción ejemplo","http://ejemplo.com","2018-09-19T10:31:12.741+02:00",true),
                      List("8e511549-b62e-4fcd-ba87-8cd92fa86ddd",295494628947L,"Headline primero","Headline segundo","Descripción ejemplo","http://ejemplo.com","2018-09-19T10:31:12.741+02:00",true),
                      List("8e511549-b62e-4fcd-ba87-8cd92fa86ddd",295494628947L,"Headline primero","Headline segundo","Descripción ejemplo","http://ejemplo.com","2018-09-19T10:31:12.741+02:00",true)
                      )
     implicit val codec: JsonValueCodec[List[ads]] = JsonCodecMaker.make[List[ads]](CodecMakerConfig())

      lazy val json:Array[Byte]  = writeToArray(adsParse(listas))
      println(new String(json, "UTF-8"))


      def adsParse(adInfo:List[List[Any]]):List[ads]={
        adInfo.map(item=>{
          ads(item.head.toString,
            item(1).toString.toLong,
            adContent(header(item(2).toString, item(3).toString), item(4).toString),
            item(5).toString,
            item(6).toString,
            item(7).toString.toBoolean
          )
        })
      }
 }
}

