package com.fiapos.weagle.domain.permissions

object Permissions {
    fun canManageStrategies(role: UserRole): Boolean {
        return role == UserRole.LEADER
    }

    fun canCreateIdeas(role: UserRole): Boolean {
        return role == UserRole.OPERATOR
    }

    fun canApproveIdeas(role: UserRole): Boolean {
        return role == UserRole.MANAGER
    }

    fun canManageProjects(role: UserRole): Boolean {
        return role == UserRole.MANAGER
    }

    fun canViewDashboard(role: UserRole): Boolean {
        return role == UserRole.LEADER
    }
}