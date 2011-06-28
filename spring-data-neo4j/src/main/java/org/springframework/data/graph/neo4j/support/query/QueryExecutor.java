/**
 * Copyright 2011 the original author or authors.
 *
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
 */

package org.springframework.data.graph.neo4j.support.query;

import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;

import java.util.Map;

/**
 * @author mh
 * @since 10.06.11
 *        todo limits
 */
public class QueryExecutor {
    private final QueryEngine queryEngine;

    public QueryExecutor(GraphDatabaseContext ctx) {
        EntityResultConverter converter = new EntityResultConverter(ctx);
        queryEngine = new EmbeddedQueryEngine(ctx.getGraphDatabaseService(), converter);
    }

    public Iterable<Map<String, Object>> query(String statement) {
        return queryEngine.query(statement);
    }

    public <T> Iterable<T> query(String statement, Class<T> type) {
        return queryEngine.query(statement).to(type);
    }

    public <T> T queryForObject(String statement, Class<T> type) {
        return queryEngine.query(statement).to(type).single();
    }

}