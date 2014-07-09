/***********************************************************************************************************************
 * Copyright (C) 2010-2013 by the Apache Flink project (http://flink.incubator.apache.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 **********************************************************************************************************************/

package eu.stratosphere.test.recordJobs.relational.query9Util;

import org.apache.flink.api.java.record.functions.MapFunction;
import org.apache.flink.types.Record;
import org.apache.flink.util.Collector;

import eu.stratosphere.test.recordJobs.util.Tuple;

@SuppressWarnings("serial")
public class PartsuppMap extends MapFunction {
	
	private Tuple inputTuple = new Tuple();
	
	/**
	 * Project "partsupp".
	 * 
	 * Output Schema:
	 *  Key: partkey
	 *  Value: (suppkey, supplycost)
	 *
	 */
	@Override
	public void map(Record record, Collector<Record> out) throws Exception {
		inputTuple = record.getField(1, inputTuple);
		inputTuple.project((0 << 0) | (1 << 1) | (0 << 2) | (1 << 3) | (0 << 4));
		record.setField(1, inputTuple);
		out.collect(record);
	}

}
