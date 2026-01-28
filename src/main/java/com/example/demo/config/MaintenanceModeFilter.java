package com.example.demo.config;

import com.example.demo.service.MaintenanceService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class MaintenanceModeFilter extends OncePerRequestFilter {

    private final MaintenanceService maintenanceService;
    public final List<String> allowPaths = List.of(
            "/api/maintenance/status",
            "/auth/**"
    );

    public MaintenanceModeFilter(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!maintenanceService.isOn()){
            filterChain.doFilter(request, response);
            return;
        }

        String path = request.getRequestURI();

        // allowlist: prefix match
        boolean allowed = allowPaths.stream().allMatch(p -> path.equals(p) || path.startsWith(p));
        if (allowed){
            filterChain.doFilter(request, response);
            return;
        }

        // allow ROLE_ADMIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth !=null && auth.isAuthenticated() && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin){
            filterChain.doFilter(request, response);
            return;
        }

        // block user
        response.setStatus(503);
        response.setContentType("application/json");
        response.getWriter().write("""
                {"code": "MAINTENANCE_MODE", "message": "Hệ thống đang bảo trì. Vui lòng quay lại sau."}
                """);
    }
}
