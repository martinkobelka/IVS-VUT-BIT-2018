/**
 * Copyright 2018 Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests;

/**
 * @author Martin Kobelka
 *
 * Profilling tests
 */

import app.Proffiling;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ProffilingTest {

    private Proffiling proffiling;
    private InputStream inputStream;

    @Before
    public void setUp() {
        proffiling = new Proffiling(null);
    }

    @Test
    public void run01() {

        String input = "5,6\n5,6";

        inputStream = new ByteArrayInputStream(input.getBytes());

        proffiling.setInputStream(inputStream);

        proffiling.getItems();

        assertEquals(0.5, proffiling.getStandartDeviation(), 1e-6);

    }

    @Test
    public void run02() {

        String input = "456456456.5, 4456456456,456, 456e10";

        inputStream = new ByteArrayInputStream(input.getBytes());

        proffiling.setInputStream(inputStream);

        proffiling.getItems();

        assertEquals(1973829564051.6, proffiling.getStandartDeviation(), 1e-1);

    }

    @Test
    public void run03() {

        String input = "5\n\n\n\n6\n\n\n\n5,f6\n\t\n\n    \t\n";

        inputStream = new ByteArrayInputStream(input.getBytes());

        proffiling.setInputStream(inputStream);

        proffiling.getItems();

        assertEquals(0.5, proffiling.getStandartDeviation(), 1e-6);

    }

    @Test
    public void run04() {

        String input = "5\n\n\n\n6\n\n\n\n5,6\n\t\n\n    \t\n";

        inputStream = new ByteArrayInputStream(input.getBytes());

        proffiling.setInputStream(inputStream);

        proffiling.getItems();

        assertEquals(0.5, proffiling.getStandartDeviation(), 1e-6);

    }


}