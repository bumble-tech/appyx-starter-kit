package com.bumble.starter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.bumble.appyx.navigation.integration.NodeActivity
import com.bumble.appyx.navigation.integration.NodeHost
import com.bumble.appyx.navigation.platform.AndroidLifecycle
import com.bumble.starter.root.RootNode
import com.bumble.starter.ui.theme.AppyxStarterKitTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppyxStarterKitTheme {
                NodeHost(
                    lifecycle = AndroidLifecycle(LocalLifecycleOwner.current.lifecycle),
                    integrationPoint = appyxV2IntegrationPoint,
                ) {
                    RootNode(buildContext = it)
                }
            }
        }
    }
}
