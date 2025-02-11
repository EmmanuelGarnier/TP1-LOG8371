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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.application.command;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nullable;
import org.sonar.process.ProcessId;
import org.sonar.process.System2;

import static java.util.Objects.requireNonNull;

public class JavaCommand<T extends JvmOptions> extends AbstractCommand<JavaCommand<T>> {
  // Program arguments
  private final Map<String, String> arguments = new LinkedHashMap<>();
  private final List<String> parameters = new LinkedList<>();
  // Entry point
  private String className;
  private JvmOptions<T> jvmOptions;
  // Relative path to JAR files
  private final List<String> classpath = new ArrayList<>();
  private boolean readsArgumentsFromFile;
  private Long gracefulStopTimeoutMs;

  /**
   * Constructs a new JavaCommand.
   *
   * @param id the process ID
   * @param workDir the working directory
   */
  public JavaCommand(ProcessId id, File workDir) {
    super(id, workDir, System2.INSTANCE);
  }

  /**
   * Returns the program arguments.
   *
   * @return the program arguments
   */
  public Map<String, String> getArguments() {
    return arguments;
  }

  /**
   * Sets a program argument.
   *
   * @param key the argument key
   * @param value the argument value
   * @return the JavaCommand instance
   */
  public JavaCommand<T> setArgument(String key, @Nullable String value) {
    if (value == null) {
      arguments.remove(key);
    } else {
      arguments.put(key, value);
    }
    return this;
  }

  /**
   * Returns the parameters.
   *
   * @return the parameters
   */
  public List<String> getParameters() {
    return parameters;
  }

  /**
   * Adds a parameter.
   *
   * @param parameter the parameter to add
   * @return the JavaCommand instance
   */
  public JavaCommand<T> addParameter(@Nullable String parameter) {
    if (parameter == null) {
      parameters.remove(parameter);
    } else {
      parameters.add(parameter);
    }
    return this;
  }

  /**
   * Returns the class name.
   *
   * @return the class name
   */
  public String getClassName() {
    return className;
  }

  /**
   * Sets the class name.
   *
   * @param className the class name to set
   * @return the JavaCommand instance
   */
  public JavaCommand<T> setClassName(String className) {
    this.className = className;
    return this;
  }

  /**
   * Returns the JVM options.
   *
   * @return the JVM options
   */
  public JvmOptions<T> getJvmOptions() {
    return jvmOptions;
  }

  /**
   * Sets the JVM options.
   *
   * @param jvmOptions the JVM options to set
   * @return the JavaCommand instance
   */
  public JavaCommand<T> setJvmOptions(JvmOptions<T> jvmOptions) {
    this.jvmOptions = jvmOptions;
    return this;
  }

  /**
   * Returns the classpath.
   *
   * @return the classpath
   */
  public List<String> getClasspath() {
    return classpath;
  }

  /**
   * Adds a classpath entry.
   *
   * @param s the classpath entry to add
   * @return the JavaCommand instance
   */
  public JavaCommand<T> addClasspath(String s) {
    classpath.add(s);
    return this;
  }

  /**
   * Returns whether arguments are read from a file.
   *
   * @return true if arguments are read from a file, false otherwise
   */
  public boolean getReadsArgumentsFromFile() {
    return readsArgumentsFromFile;
  }

  /**
   * Sets whether arguments are read from a file.
   *
   * @param readsArgumentsFromFile true if arguments are read from a file, false otherwise
   * @return the JavaCommand instance
   */
  public JavaCommand<T> setReadsArgumentsFromFile(boolean readsArgumentsFromFile) {
    this.readsArgumentsFromFile = readsArgumentsFromFile;
    return this;
  }

  /**
   * Sets multiple arguments.
   *
   * @param args the arguments to set
   * @return the JavaCommand instance
   */
  public JavaCommand<T> setArguments(Properties args) {
    for (Map.Entry<Object, Object> entry : args.entrySet()) {
      setArgument(entry.getKey().toString(), entry.getValue() != null ? entry.getValue().toString() : null);
    }
    return this;
  }

  /**
   * Returns the graceful stop timeout in milliseconds.
   *
   * @return the graceful stop timeout in milliseconds
   */
  public long getGracefulStopTimeoutMs() {
    requireNonNull(gracefulStopTimeoutMs, "gracefulStopTimeoutMs has not been set");
    return gracefulStopTimeoutMs;
  }

  /**
   * Sets the graceful stop timeout in milliseconds.
   *
   * @param gracefulStopTimeoutMs the graceful stop timeout in milliseconds
   * @return the JavaCommand instance
   */
  public JavaCommand<T> setGracefulStopTimeoutMs(long gracefulStopTimeoutMs) {
    this.gracefulStopTimeoutMs = gracefulStopTimeoutMs;
    return this;
  }

  @Override
  public String toString() {
    return "JavaCommand{" + "workDir=" + getWorkDir() +
      ", arguments=" + arguments +
      ", className='" + className + '\'' +
      ", jvmOptions=" + jvmOptions +
      ", classpath=" + classpath +
      ", readsArgumentsFromFile=" + readsArgumentsFromFile +
      ", gracefulStopTimeoutMs=" + gracefulStopTimeoutMs +
      ", envVariables=" + getEnvVariables() +
      ", suppressedEnvVariables=" + getSuppressedEnvVariables() +
      '}';
  }
}
