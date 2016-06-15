import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import pom.ProductsListPage

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification with  ProductsListPage{
  "Application" should {
    "send 404 on a bad request" in new WithApplication{
      val bad = route(FakeRequest(GET, "/boum")).get
      status(bad) must equalTo(404)
      contentType(bad) must beSome.which(_ == "text/html")
      contentAsString(bad) must contain ("404 - Page not found.")
      contentAsString(bad) must contain ("Selected path do not exists: /boum")
      contentAsString(bad) must contain (" E-Biznes Online Store Project")
    }
    "render the main page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Smartfon")
      contentAsString(home) must contain ("AGD")
      contentAsString(home) must contain ("Computers")
      contentAsString(home) must contain ("Foto")
      contentAsString(home) must contain ("journey")
      contentAsString(home) must contain ("")
      contentAsString(home) must contain ("Category 5")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the contact page" in new WithApplication{
      val home = route(FakeRequest(GET, "/contact")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Contact")
      contentAsString(home) must contain ("Your Name")
      contentAsString(home) must contain ("Your Email")
      contentAsString(home) must contain ("Message")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the show_users page" in new WithApplication{
      val home = route(FakeRequest(GET, "/show_users")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Users")
      contentAsString(home) must contain ("ID")
      contentAsString(home) must contain ("Login")
      contentAsString(home) must contain ("Email")
      contentAsString(home) must not contain ("Password")
      contentAsString(home) must contain ("Name")
      contentAsString(home) must contain ("City")
      contentAsString(home) must contain ("Date of birth")
      contentAsString(home) must contain ("Option")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the update_user page" in new WithApplication{
      val home = route(FakeRequest(GET, "/update_user/1")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Users -> Update User")
      contentAsString(home) must not contain ("ID")
      contentAsString(home) must contain ("Email address")
      contentAsString(home) must contain ("Login")
      contentAsString(home) must contain ("Password")
      contentAsString(home) must contain ("Name")
      contentAsString(home) must contain ("Date of birth")
      contentAsString(home) must contain ("City")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the create_user page" in new WithApplication{
      val home = route(FakeRequest(GET, "/create_user")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Users -> Add new User")
      contentAsString(home) must not contain ("ID")
      contentAsString(home) must contain ("Email address")
      contentAsString(home) must contain ("Login")
      contentAsString(home) must contain ("Password")
      contentAsString(home) must contain ("Name")
      contentAsString(home) must contain ("Date of birth")
      contentAsString(home) must contain ("City")
      contentAsString(home) must contain ("Accept our rules!")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the show_products page" in new WithApplication{
      val home = route(FakeRequest(GET, "/show_products")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("products")
      contentAsString(home) must contain ("ID")
      contentAsString(home) must contain ("Name")
      contentAsString(home) must contain ("Description")
      contentAsString(home) must contain ("Price")
      contentAsString(home) must contain ("Category")
      contentAsString(home) must contain ("Option")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the create_product page" in new WithApplication{
      val home = route(FakeRequest(GET, "/create_product")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("products -> Add new Product")
      contentAsString(home) must not contain ("ID")
      contentAsString(home) must contain ("Name")
      contentAsString(home) must contain ("Description")
      contentAsString(home) must contain ("Price")
      contentAsString(home) must contain ("Category")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
    "render the update_product page" in new WithApplication{
      val home = route(FakeRequest(GET, "/update_product/1")).get
      status(home) must equalTo(200)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("products -> Update Product")
      contentAsString(home) must not contain ("ID")
      contentAsString(home) must contain ("Name")
      contentAsString(home) must contain ("Description")
      contentAsString(home) must contain ("Price")
      contentAsString(home) must contain ("Category")
      contentAsString(home) must contain (" E-Biznes Online Store Project")
    }
  }
}