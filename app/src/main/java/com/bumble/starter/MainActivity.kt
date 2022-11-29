package com.bumble.starter

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.starter.root.RootNode
import com.bumble.starter.ui.theme.AppyxStarterKitTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppyxStarterKitTheme {
                NodeHost(integrationPoint = appyxIntegrationPoint) {
                    RootNode(buildContext = it)
                }
            }
        }
    }
}
