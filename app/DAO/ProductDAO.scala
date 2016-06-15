
package DAO

import scala.concurrent.Future
import models.Product
import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

class ProductDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Products = TableQuery[ProductsTable]

  def all(): Future[Seq[Product]] = db.run(Products.result)

  def filterbyCategory(kat: String): Future[Seq[Product]] = db.run(Products.filter(_.kat === kat).result)

  def insert(product: Product): Future[Unit] = db.run(Products += product).map { _ => () }

  def delete(id: Int): Future[Unit] = db.run(Products.filter(_.id === id).delete).map(_ => ())

  def findById(id: Int): Future[Option[Product]] = db.run(Products.filter(_.id === id).result.headOption)

  def update(product: Product): Future[Unit] = {
    val id = product.ID
    db.run(Products.filter(_.id === id).update(product)).map(_ => ())
  }

  private class ProductsTable(tag: Tag) extends Table[Product](tag, "PRODUCT") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def description = column[String]("DESCRIPTION")
    def price = column[Int]("PRICE")
    def kat = column[String]("KAT")
    def * = (id.?, name, description, price, kat) <> (Product.tupled, Product.unapply _)
  }

}

