
package DAO

import scala.concurrent.Future
import models.Category
import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

class CategoryDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Categories = TableQuery[CategoryTable]

  def all(): Future[Seq[Category]] = db.run(Categories.result)

  def insert(cat: Category): Future[Unit] = db.run(Categories += cat).map { _ => () }

  def delete(id: Int): Future[Unit] = db.run(Categories.filter(_.id === id).delete).map(_ => ())

  def findById(id: Int): Future[Option[Category]] = db.run(Categories.filter(_.id === id).result.headOption)

  //def update( cat: Category): Future[Unit] = {
  // val cat=cat.ID
  // db.run(Categories.filter(_.id === cat).update(cat)).map(_ => ())
  //}

  private class CategoryTable(tag: Tag) extends Table[Category](tag, "CATEGORY") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")

    def * = (id.?, name) <> (Category.tupled, Category.unapply _)
  }

}

