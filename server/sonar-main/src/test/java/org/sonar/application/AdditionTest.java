/*
 * SonarQube
 * Copyright (C) 2009-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

*
package org.sonar.application;

**

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bytebuddy.implementation.bytecode.Addition;**

public class AdditionTest {
    **
   @Test
 * public void testAddition() {
 * int result = Addition.addNumbers(5, 10);
 * assertEquals(15, result);
 * }**

   @Test
 * public void testNegativeNumberAdditiion() {
 * int result = Addition.addNumbers(-23, -9);
 * assertEquals(-32, result);
 * }***

   @Test
 * public void testResultZero() {
 * int result = Addition.addNumbers(-4, 4);
 * assertEquals(0, result);
 * }*
}
