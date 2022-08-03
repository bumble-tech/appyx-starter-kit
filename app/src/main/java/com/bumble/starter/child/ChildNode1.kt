package com.bumble.starter.child

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.starter.child.view.ChildView
import com.bumble.starter.ui.theme.md_grey_900
import com.bumble.starter.ui.theme.md_yellow_700

class ChildNode1(buildContext: BuildContext) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {

        ChildView(
            text = "This is Child 1",
            textColor = md_grey_900,
            backgroundColor = md_yellow_700
        )
    }
}