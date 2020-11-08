package com.example.checks

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

class FooDetector : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames() =
            listOf("foo")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        reportUsage(context, node)
    }

    private fun reportUsage(context: JavaContext, node: UCallExpression) {
        context.report(
                issue = ISSUE,
                scope = node,
                location = context.getCallLocation(
                        call = node,
                        includeReceiver = true,
                        includeArguments = true
                ),
                message = description
        )
    }

    companion object {
        private const val description = "Avoid MainActivity.foo()"
        val ISSUE = Issue.create(
                id = "AvoidMainActivityFoo",
                briefDescription = description,
                explanation = "MainActivity.foo() should rarely be used.",
                category = Category.CORRECTNESS,
                priority = 6,
                severity = Severity.INFORMATIONAL,
                enabledByDefault = true,
                androidSpecific = true,
                implementation = Implementation(FooDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

}