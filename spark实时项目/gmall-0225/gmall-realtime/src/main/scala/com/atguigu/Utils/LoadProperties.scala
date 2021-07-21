package com.atguigu.Utils

import java.io.InputStream
import java.util.Properties

object LoadProperties {
  def loadProperties() = {
    val properties = new Properties()
    val inputStream: InputStream = Thread.currentThread().getContextClassLoader.getResourceAsStream("config.properties")
    properties.load(inputStream)
    properties
  }
}
