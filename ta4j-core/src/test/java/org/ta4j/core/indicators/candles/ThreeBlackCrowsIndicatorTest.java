/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 Marc de Verdelhan, 2017-2019 Ta4j Organization & respective
 * authors (see AUTHORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.candles;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.mocks.MockBar;
import org.ta4j.core.mocks.MockTimeSeries;
import org.ta4j.core.num.Num;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ThreeBlackCrowsIndicatorTest extends AbstractIndicatorTest<Indicator<Boolean>, Num> {

    private TimeSeries series;

    public ThreeBlackCrowsIndicatorTest(Function<Number, Num> numFunction) {
        super(numFunction);
    }

    @Before
    public void setUp() {
        List<Bar> bars = new ArrayList<Bar>();
        // open, close, high, low
        bars.add(new MockBar(19, 19, 22, 15,numFunction));
        bars.add(new MockBar(10, 18, 20, 8,numFunction));
        bars.add(new MockBar(17, 20, 21, 17,numFunction));
        bars.add(new MockBar(19, 17, 20, 16.9,numFunction));
        bars.add(new MockBar(17.5, 14, 18, 13.9,numFunction));
        bars.add(new MockBar(15, 11, 15, 11,numFunction));
        bars.add(new MockBar(12, 14, 15, 8,numFunction));
        bars.add(new MockBar(13, 16, 16, 11,numFunction));
        series = new MockTimeSeries(bars);
    }

    @Test
    public void getValue() {
        ThreeBlackCrowsIndicator tbc = new ThreeBlackCrowsIndicator(series, 3, 0.1);
        assertFalse(tbc.getValue(0));
        assertFalse(tbc.getValue(1));
        assertFalse(tbc.getValue(2));
        assertFalse(tbc.getValue(3));
        assertFalse(tbc.getValue(4));
        assertTrue(tbc.getValue(5));
        assertFalse(tbc.getValue(6));
        assertFalse(tbc.getValue(7));
    }
}
