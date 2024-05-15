package ma.zs.GestionAgents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.GestionAgents.zynerator.security.bean.*;
import ma.zs.GestionAgents.zynerator.security.common.AuthoritiesConstants;
import ma.zs.GestionAgents.zynerator.security.service.facade.*;


import ma.zs.GestionAgents.zynerator.security.bean.User;
import ma.zs.GestionAgents.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class GestionAgentsApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(GestionAgentsApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService) {
    return (args) -> {
        if(true){


        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));


        

		// User Utilisateur
		User userForUtilisateur = new User("utilisateur");
		userForUtilisateur.setPassword("123");
		// Role Utilisateur
		Role roleForUtilisateur = new Role();
		roleForUtilisateur.setAuthority(AuthoritiesConstants.UTILISATEUR);
		roleForUtilisateur.setCreatedAt(LocalDateTime.now());
		Role roleForUtilisateurSaved = roleService.create(roleForUtilisateur);
		RoleUser roleUserForUtilisateur = new RoleUser();
		roleUserForUtilisateur.setRole(roleForUtilisateurSaved);
		if (userForUtilisateur.getRoleUsers() == null)
			userForUtilisateur.setRoleUsers(new ArrayList<>());

		userForUtilisateur.getRoleUsers().add(roleUserForUtilisateur);
		if (userForUtilisateur.getModelPermissionUsers() == null)
			userForUtilisateur.setModelPermissionUsers(new ArrayList<>());


        userForUtilisateur.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForUtilisateur);

        

		// User Admin
		User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("Agent"));
        modelPermissions.add(new ModelPermission("Pointage"));
        modelPermissions.add(new ModelPermission("DemandeConge"));
        modelPermissions.add(new ModelPermission("Retard"));
        modelPermissions.add(new ModelPermission("EntiteAdministrative"));
        modelPermissions.add(new ModelPermission("DemandeAbsence"));
        modelPermissions.add(new ModelPermission("BadgeNfc"));
        modelPermissions.add(new ModelPermission("RaisonDemandeConge"));
        modelPermissions.add(new ModelPermission("Poste"));
        modelPermissions.add(new ModelPermission("Conge"));
        modelPermissions.add(new ModelPermission("HeureSupplementaire"));
        modelPermissions.add(new ModelPermission("RaisonDemandeAbsence"));
        modelPermissions.add(new ModelPermission("Absence"));
        modelPermissions.add(new ModelPermission("DemandeDocumentAdministratif"));
        modelPermissions.add(new ModelPermission("PlageHoraire"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


