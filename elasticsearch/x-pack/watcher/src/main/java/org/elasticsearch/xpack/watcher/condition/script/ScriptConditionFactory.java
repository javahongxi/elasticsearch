/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.watcher.condition.script;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.xpack.watcher.condition.ConditionFactory;

import java.io.IOException;

/**
 *
 */
public class ScriptConditionFactory extends ConditionFactory<ScriptCondition, ScriptCondition.Result, ExecutableScriptCondition> {

    private final ScriptService scriptService;

    @Inject
    public ScriptConditionFactory(Settings settings, ScriptService service) {
        super(Loggers.getLogger(ExecutableScriptCondition.class, settings));
        scriptService = service;
    }

    @Override
    public String type() {
        return ScriptCondition.TYPE;
    }

    @Override
    public ScriptCondition parseCondition(String watchId, XContentParser parser) throws IOException {
        return ScriptCondition.parse(watchId, parser);
    }

    @Override
    public ExecutableScriptCondition createExecutable(ScriptCondition condition) {
        return new ExecutableScriptCondition(condition, conditionLogger, scriptService);
    }
}
