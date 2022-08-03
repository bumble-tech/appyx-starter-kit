package com.bumble.starter.child

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.starter.child.view.ChildView
import com.bumble.starter.ui.theme.md_deep_purple_50
import com.bumble.starter.ui.theme.md_deep_purple_700

class ChildNode2(
    buildContext: BuildContext,
    private val onSwitchNow: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        ChildView(
            text = "This is Child 2",
            textColor = md_deep_purple_50,
            backgroundColor = md_deep_purple_700,
            onSwitchNow = onSwitchNow
        )
    }
}