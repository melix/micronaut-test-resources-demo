/*
 * Copyright 2003-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Singleton
public class Greeter {
    private final String name;

    public Greeter(@Value("${my.user.name}") String name) {
        this.name = name;
    }

    public String getGreeting() {
        return "Hello, " + name + "!";
    }

    public void sayHello() {
        System.out.println(getGreeting());
    }
}
