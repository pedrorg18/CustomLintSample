package com.example.checks

import com.android.tools.lint.detector.api.Issue

class IssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(
            FooDetector.ISSUE
        )

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API
}