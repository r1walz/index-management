/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazon.opendistroforelasticsearch.indexstatemanagement.model

import com.amazon.opendistroforelasticsearch.indexstatemanagement.models.Conditions
import com.amazon.opendistroforelasticsearch.indexstatemanagement.randomAge
import com.amazon.opendistroforelasticsearch.indexstatemanagement.randomByteSizeValue
import org.elasticsearch.test.ESTestCase
import kotlin.test.assertFailsWith

class ConditionsTests : ESTestCase() {

    fun `test supplying more than one transition condition fails`() {
        assertFailsWith(
            IllegalArgumentException::class,
            "Expected IllegalArgumentException for supplying multiple transition conditions") {
            Conditions(indexAge = randomAge(), size = randomByteSizeValue())
        }
    }

    fun `test invalid index age condition fails`() {
        assertFailsWith(
            IllegalArgumentException::class,
            "Expected IllegalArgumentException for invalid index age condition") {
            Conditions(indexAge = "invalidIndexAge")
        }
    }

    fun `test invalid size condition fails`() {
        assertFailsWith(
            IllegalArgumentException::class,
            "Expected IllegalArgumentException for invalid size condition") {
            Conditions(indexAge = "invalidIndexAge")
        }
    }

    fun `test size condition of zero fails`() {
        assertFailsWith(
            IllegalArgumentException::class,
            "Expected IllegalArgumentException for size condition less than 1") {
            Conditions(size = "0")
        }
    }
}