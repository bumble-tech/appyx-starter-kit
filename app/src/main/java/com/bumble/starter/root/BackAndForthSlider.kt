package com.bumble.starter.root

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.bumble.appyx.core.routing.transition.ModifierTransitionHandler
import com.bumble.appyx.core.routing.transition.TransitionDescriptor
import com.bumble.appyx.core.routing.transition.TransitionSpec
import com.bumble.appyx.routingsource.backstack.BackStack
import com.bumble.appyx.routingsource.backstack.operation.BackStackOperation
import com.bumble.appyx.routingsource.backstack.operation.Replace

@Suppress("TransitionPropertiesLabel")
class BackAndForthSlider<T>(
    private val transitionSpec: TransitionSpec<BackStack.TransitionState, Offset> = {
        spring(stiffness = Spring.StiffnessVeryLow)
    },
    override val clipToBounds: Boolean = false
) : ModifierTransitionHandler<T, BackStack.TransitionState>() {

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.TransitionState>,
        descriptor: TransitionDescriptor<T, BackStack.TransitionState>
    ): Modifier = modifier.composed {
        val offset = transition.animateOffset(
            transitionSpec = transitionSpec,
            targetValueByState = {
                val width = descriptor.params.bounds.width.value
                when (it) {
                    BackStack.TransitionState.CREATED -> {
                        val element = descriptor.element
                        if (element is RootNode.Routing.Child1) {
                            toOutsideLeft(width)
                        } else {
                            toOutsideRight(width)
                        }
                    }
                    BackStack.TransitionState.ACTIVE -> toCenter()
                    BackStack.TransitionState.STASHED_IN_BACK_STACK -> toOutsideLeft(width)
                    BackStack.TransitionState.DESTROYED -> {
                        when (val operation = descriptor.operation as? BackStackOperation) {
                            is Replace -> {
                                val element = descriptor.element
                                if (element is RootNode.Routing.Child1) {
                                    toOutsideLeft(width)
                                } else {
                                    toOutsideRight(width)
                                }
                            }
                            else -> error("Unexpected operation: $operation")
                        }
                    }
                }
            },
        )

        offset(Dp(offset.value.x), Dp(offset.value.y))
    }

    private fun toOutsideRight(width: Float) = Offset(1.0f * width, 0f)

    private fun toOutsideLeft(width: Float) = Offset(-1.0f * width, 0f)

    private fun toCenter() = Offset(0f, 0f)
}

@Composable
fun <T> rememberBackAndForthSlider(
    transitionSpec: TransitionSpec<BackStack.TransitionState, Offset> = { spring(stiffness = Spring.StiffnessVeryLow) },
    clipToBounds: Boolean = false
): ModifierTransitionHandler<T, BackStack.TransitionState> = remember {
    BackAndForthSlider(transitionSpec = transitionSpec, clipToBounds = clipToBounds)
}