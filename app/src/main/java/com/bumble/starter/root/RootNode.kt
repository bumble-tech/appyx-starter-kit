package com.bumble.starter.root

import android.os.Parcelable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.coroutineScope
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.activeElement
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import com.bumble.starter.child.ChildNode1
import com.bumble.starter.child.ChildNode2
import com.bumble.starter.root.RootNode.NavTarget
import com.bumble.starter.root.RootNode.NavTarget.Child1
import com.bumble.starter.root.RootNode.NavTarget.Child2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = Child1,
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<NavTarget>(
    buildContext = buildContext,
    navModel = backStack
) {

    sealed class NavTarget : Parcelable {
        @Parcelize
        object Child1 : NavTarget()

        @Parcelize
        object Child2 : NavTarget()
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        when (navTarget) {
            is Child1 -> ChildNode1(buildContext)
            is Child2 -> ChildNode2(buildContext, ::swapChildren)
        }

    init {
        initAnimation()
    }

    private fun initAnimation() {
        lifecycle.coroutineScope.launch {
            while (true) {
                delay(2000)
                swapChildren()
            }
        }
    }

    private fun swapChildren() {
        if (backStack.activeElement == Child1) {
            backStack.push(Child2)
        } else {
            backStack.pop()
        }
    }

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Children(
                navModel = backStack,
                transitionHandler = rememberBackstackFader { spring(stiffness = Spring.StiffnessVeryLow) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
