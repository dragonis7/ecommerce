package pom

import play.api.test.TestBrowser;

trait ProductsListPage
{

  def getProductsCount(browser:TestBrowser): Int={

  browser.find(".product").size()
}


  def getProductsName(browser:TestBrowser, product_id:Int):String={

    "test"
  }



}

