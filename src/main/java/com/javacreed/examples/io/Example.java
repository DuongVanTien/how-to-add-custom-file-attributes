/*
 * #%L
 * How to Add Custom File Attributes
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 - 2015 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.javacreed.examples.io;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Example {

  public static void main(final String[] args) throws Exception {
    final Path file = Paths.get(Example.class.getResource("/samples/example.txt").toURI()).toAbsolutePath();

    final UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);

    // The file attribute
    final String name = "com.javacreed.attr.1";
    final String value = "Custom Value 1";

    // Write the properties
    final byte[] bytes = value.getBytes("UTF-8");
    final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
    writeBuffer.put(bytes);
    writeBuffer.flip();
    view.write(name, writeBuffer);

    // Read the property
    final ByteBuffer readBuffer = ByteBuffer.allocate(view.size(name));
    view.read(name, readBuffer);
    readBuffer.flip();
    final String valueFromAttributes = new String(readBuffer.array(), "UTF-8");
    System.out.println("File Attribute: " + valueFromAttributes);
  }
}
