/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.orchestration.core.configuration;

import org.apache.shardingsphere.underlying.common.config.DataSourceConfiguration;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class DataSourceConfigurationYamlSwapperTest {
    
    private final DataSourceConfigurationYamlSwapper dataSourceConfigurationYamlSwapper = new DataSourceConfigurationYamlSwapper();
    
    @Test
    public void assertSwapToYaml() {
        DataSourceConfiguration dataSourceConfiguration = new DataSourceConfiguration("xxx.jdbc.driver");
        dataSourceConfiguration.getProperties().put("url", "xx:xxx");
        dataSourceConfiguration.getProperties().put("username", "root");
        YamlDataSourceConfiguration actual = dataSourceConfigurationYamlSwapper.swap(dataSourceConfiguration);
        assertThat(actual.getDataSourceClassName(), is("xxx.jdbc.driver"));
        assertThat(actual.getProperties().size(), is(2));
        assertThat(actual.getProperties().get("url").toString(), is("xx:xxx"));
        assertThat(actual.getProperties().get("username").toString(), is("root"));
    }
    
    @Test
    public void assertSwapToConfiguration() {
        YamlDataSourceConfiguration yamlConfiguration = new YamlDataSourceConfiguration();
        yamlConfiguration.setDataSourceClassName("xxx.jdbc.driver");
        yamlConfiguration.getProperties().put("url", "xx:xxx");
        yamlConfiguration.getProperties().put("username", "root");
        DataSourceConfiguration actual = dataSourceConfigurationYamlSwapper.swap(yamlConfiguration);
        assertThat(actual.getDataSourceClassName(), is("xxx.jdbc.driver"));
        assertThat(actual.getProperties().size(), is(2));
        assertThat(actual.getProperties().get("url").toString(), is("xx:xxx"));
        assertThat(actual.getProperties().get("username").toString(), is("root"));
    }
}
