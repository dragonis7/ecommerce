/**
 * Created by beduin90 on 18.04.2016.
 */
package controllers

import DAO.{ CategoryDAO, ProductDAO }
import com.mohiva.play.silhouette.api.Silhouette
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import models.{ Category, Product, User }
import play.api._
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.data.Forms.number
import play.api.data.Forms.optional
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.mvc._
import javax.inject.Inject

import com.mohiva.play.silhouette
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.auth.DefaultEnv

import scala.concurrent.Future
import play.api.libs.json
import play.filters.csrf.CSRF

class Application @Inject() (productDAO: ProductDAO)(catDAO: CategoryDAO)(
  val messagesApi: MessagesApi,
  silhouette: Silhouette[DefaultEnv],
  socialProviderRegistry: SocialProviderRegistry,
  implicit val webJarAssets: WebJarAssets

)

    extends Controller with I18nSupport {

  //Product START
  val productForm = Form(
    mapping(
      "id" -> optional(number),
      "name" -> text,
      "description" -> text,
      "price" -> number,
      "kat" -> text
    )(Product.apply)(Product.unapply)
  )

  def template = silhouette.UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.template(request.identity)))
  }

  def contact = silhouette.UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.contact(request.identity)))

  }

  def shopping = silhouette.UserAwareAction.async {
    Future.successful(Ok(views.html.shoppping_card()))
  }

  def payment = silhouette.UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.payment(request.identity)))
  }

  def confirm = silhouette.UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.confirm(request.identity)))
  }

  def order = silhouette.UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.order(request.identity)))
  }

  def cart = silhouette.UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.cart(request.identity)))
  }

  def filterProducts = silhouette.UserAwareAction.async { implicit request =>
    productDAO.all().map(products => Ok(views.html.filterProducts(products, request.identity)))
  }

  def filterCategory(k: String) = silhouette.UserAwareAction.async { implicit request =>
    productDAO.filterbyCategory(k).map(products => Ok(views.html.filterProducts(products, request.identity)))
  }

  def createproduct = silhouette.SecuredAction.async { implicit request =>
    import play.filters.csrf._
    Future.successful(Ok(views.html.createproduct(CSRF.getToken(request).map(_.value).getOrElse(""), request.identity)))
  }

  def newproduct = silhouette.SecuredAction.async { implicit request =>
    val product: models.Product = productForm.bindFromRequest().get
    productDAO.insert(product)
    Future.successful(Ok(views.html.newproduct(product)))
  }

  def deleteproduct(id: Int) = silhouette.SecuredAction { implicit request =>
    productDAO.delete(id)
    Redirect(routes.Application.filterProducts())
  }

  def applyproductchanges = silhouette.SecuredAction { implicit request =>
    val updatedProduct: models.Product = productForm.bindFromRequest().get
    productDAO.update(updatedProduct)
    Redirect(routes.Application.filterCategory(updatedProduct.kat))
  }

  def updateproduct(id: Int) = silhouette.SecuredAction.async { implicit request =>
    import play.filters.csrf._
    val computerAndOptions = for {

      product <- productDAO.findById(id)
    } yield (product)

    computerAndOptions.map {
      case (computer) =>
        computer match {
          case Some(product) => Ok(views.html.updateproduct(CSRF.getToken(request).map(_.value).getOrElse(""), product, request.identity))
          case None => NotFound
        }
    }

  }

  // Product END

  //category start
  val CatForm = Form(
    mapping(
      "id" -> optional(number),
      "name" -> text()

    )(Category.apply)(Category.unapply)
  )

  //category end

  //shoppign cart start
  def shoppingCard = Action {
    Ok(views.html.shoppping_card.render())
  }

}

