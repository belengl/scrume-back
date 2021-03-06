
package com.spring.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.Model.Box;
import com.spring.Model.Column;
import com.spring.Model.Document;
import com.spring.Model.DocumentType;
import com.spring.Model.Estimation;
import com.spring.Model.HistoryTask;
import com.spring.Model.Invitation;
import com.spring.Model.Notification;
import com.spring.Model.Payment;
import com.spring.Model.Note;
import com.spring.Model.Project;
import com.spring.Model.SecurityBreach;
import com.spring.Model.Sprint;
import com.spring.Model.Task;
import com.spring.Model.Team;
import com.spring.Model.User;
import com.spring.Model.UserAccount;
import com.spring.Model.UserRol;
import com.spring.Model.Workspace;
import com.spring.Repository.BoxRepository;
import com.spring.Repository.ColumnRepository;
import com.spring.Repository.DocumentRepository;
import com.spring.Repository.EstimationRepository;
import com.spring.Repository.HistoryTaskRepository;
import com.spring.Repository.InvitationRepository;
import com.spring.Repository.NotificationRepository;
import com.spring.Repository.PaymentRepository;
import com.spring.Repository.NoteRepository;
import com.spring.Repository.ProjectRepository;
import com.spring.Repository.SecurityBreachRepository;
import com.spring.Repository.SprintRepository;
import com.spring.Repository.TaskRepository;
import com.spring.Repository.TeamRepository;
import com.spring.Repository.UserRepository;
import com.spring.Repository.UserRolRepository;
import com.spring.Repository.WorkspaceRepository;
import com.spring.Security.Role;
import com.spring.Security.UserAccountRepository;
import com.spring.Utiles.Utiles;

/**
 * 
 * Uncomment this annotation if you want to repopulate database. Please, take
 * into account you will have to include entities in order.
 *
 */

@Component
public class PopulatorDatabase implements CommandLineRunner {

	protected final Logger log = Logger.getLogger(PopulatorDatabase.class);

	@Autowired
	private UserAccountRepository accountRepository;

	@Autowired
	private BoxRepository boxRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private ColumnRepository columnRepository;

	@Autowired
	private UserRolRepository userRolRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private InvitationRepository invitationRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private HistoryTaskRepository historyTaskRepository;

	@Autowired
	private EstimationRepository estimationRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private SecurityBreachRepository securityBreachRepository;

	@Override
	public void run(String... args) throws Exception {

		final String properties = "entities.properties";

		SortedMap<String, Integer> entities = new TreeMap<>();
		Utiles.escribeFichero(entities, properties);

		securityBreachRepository.deleteAll();
		notificationRepository.deleteAll();
		historyTaskRepository.deleteAll();
		estimationRepository.deleteAll();
		paymentRepository.deleteAll();
		invitationRepository.deleteAll();
		taskRepository.deleteAll();
		userRolRepository.deleteAll();
		columnRepository.deleteAll();
		workspaceRepository.deleteAll();
		sprintRepository.deleteAll();
		projectRepository.deleteAll();
		teamRepository.deleteAll();
		userRepository.deleteAll();
		boxRepository.deleteAll();
		accountRepository.deleteAll();
		documentRepository.deleteAll();
		noteRepository.deleteAll();

		UserAccount account0 = accountRepository
				.save(new UserAccount("administrator@gmail.com", Utiles.encryptedPassword("1234560"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_ADMIN))));
		
		UserAccount account1 = accountRepository
				.save(new UserAccount("testuser1@gmail.com", Utiles.encryptedPassword("1234561"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));

		UserAccount account2 = accountRepository
				.save(new UserAccount("testuser2@gmail.com", Utiles.encryptedPassword("1234562"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));

		UserAccount account3 = accountRepository
				.save(new UserAccount("testuser3@gmail.com", Utiles.encryptedPassword("1234563"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));

		UserAccount account4 = accountRepository
				.save(new UserAccount("testuser4@gmail.com", Utiles.encryptedPassword("1234564"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		
		entities.put("account0", account0.getId());
		entities.put("account1", account1.getId());
		entities.put("account2", account2.getId());
		entities.put("account3", account3.getId());
		entities.put("account4", account4.getId());
		
		//USERACCOUNTS CON PAQUETE CADUCADO
		
		UserAccount account5 = accountRepository
				.save(new UserAccount("testuser5@gmail.com", Utiles.encryptedPassword("1234565"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		UserAccount account6 = accountRepository
				.save(new UserAccount("testuser6@gmail.com", Utiles.encryptedPassword("1234566"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		UserAccount account7 = accountRepository
				.save(new UserAccount("testuser7@gmail.com", Utiles.encryptedPassword("1234567"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		UserAccount account8 = accountRepository
				.save(new UserAccount("testuser8@gmail.com", Utiles.encryptedPassword("1234568"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		UserAccount account9 = accountRepository
				.save(new UserAccount("testuser9@gmail.com", Utiles.encryptedPassword("1234569"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		

		entities.put("account5", account5.getId());
		entities.put("account6", account6.getId());
		entities.put("account7", account7.getId());
		entities.put("account8", account8.getId());
		entities.put("account9", account9.getId());

		//USERACCOUNTS CON PAQUETE BASIC
		UserAccount account10 = accountRepository
				.save(new UserAccount("testuser10@gmail.com", Utiles.encryptedPassword("12345610"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
		UserAccount account11 = accountRepository
				.save(new UserAccount("testuser11@gmail.com", Utiles.encryptedPassword("12345611"), LocalDateTime.now(),
						LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
	
		entities.put("account10", account10.getId());
		entities.put("account11", account11.getId());
		
		//USERACCOUNTS CON PAQUETE STANDARD
				UserAccount account12 = accountRepository
						.save(new UserAccount("testuser12@gmail.com", Utiles.encryptedPassword("12345612"), LocalDateTime.now(),
								LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
				UserAccount account13 = accountRepository
						.save(new UserAccount("testuser13@gmail.com", Utiles.encryptedPassword("12345613"), LocalDateTime.now(),
								LocalDateTime.now(), new HashSet<Role>(Arrays.asList(Role.ROLE_USER))));
			
				entities.put("account12", account12.getId());
				entities.put("account13", account13.getId());
		
		Box basicBox = boxRepository.save(new Box("BASIC", 0.0));
		Box standardBox = boxRepository.save(new Box("STANDARD", 1.0));
		Box proBox = boxRepository.save(new Box("PRO", 2.0));

		entities.put("basicBox", basicBox.getId());
		entities.put("standardBox", standardBox.getId());
		entities.put("proBox", proBox.getId());

		User user0 = new User("ADMINISTRATOR", "administrator", "ADMIN1", "ADMIN2", null);
		user0.setUserAccount(account0);
		user0 = userRepository.save(user0);
		User user1 = new User("Juan María", "Lorenzo Pérez", "jualorper", "jualorper", null);
		user1.setUserAccount(account1);
		user1 = userRepository.save(user1);
		User user2 = new User("Ángel", "Delgado Luna", "angdellun", "angel96", null);
		user2.setUserAccount(account2);
		user2 = userRepository.save(user2);
		User user3 = new User("Ezequiel", "Portillo Jurado", "ezeporjur", "EzequielPJ", null);
		user3.setUserAccount(account3);
		user3 = userRepository.save(user3);
		User user4 = new User("Manuel Cecilio", "Pérez Gutiérrez", "manpergut", "manelcecs", null);
		user4.setUserAccount(account4);
		user4 = userRepository.save(user4);

		entities.put("user0", user0.getId());
		entities.put("user1", user1.getId());
		entities.put("user2", user2.getId());
		entities.put("user3", user3.getId());
		entities.put("user4", user4.getId());

		
		//USERS CON PAQUETE CADUCADO 
		
		User user5 = new User("Juan", "Perez Alvarez", "juaperalv", "juaperalv", null);
		user5.setUserAccount(account5);
		user5 = userRepository.save(user5);

		User user6 = new User("Manuel", "Benitez Carranco", "manbencar", "manbencar", null);
		user6.setUserAccount(account6);
		user6 = userRepository.save(user6);

		User user7 = new User("Inma", "Gutierrez Martinez", "inmgutmar", "inmgutmar", null);
		user7.setUserAccount(account7);
		user7 = userRepository.save(user7);

		User user8 = new User("Laura", "Mora Ruiz", "laumorrui", "laumorrui", null);
		user8.setUserAccount(account8);
		user8 = userRepository.save(user8);

		User user9 = new User("Maria", "Gracia Montes", "margramon", "margramon", null);
		user9.setUserAccount(account9);
		user9 = userRepository.save(user9);

		
		entities.put("user5", user5.getId());
		entities.put("user6", user6.getId());
		entities.put("user7", user7.getId());
		entities.put("user8", user8.getId());
		entities.put("user9", user9.getId());
		
		//USERS CON PAQUETE BASIC 
		
		User user10 = new User("Pilar", "Marquez Carmona", "pilmarcar", "pilmarcar", null);
		user10.setUserAccount(account10);
		user10 = userRepository.save(user10);

		User user11 = new User("Rafael", "Gil Corchuelo", "rafgilcor", "rafgilcor", null);
		user11.setUserAccount(account11);
		user11 = userRepository.save(user11);

		
		entities.put("user10", user10.getId());
		entities.put("user11", user11.getId());
		
		//USERS CON PAQUETE STANDARD 
		
		User user12 = new User("Alejandro", "Bejarano Fuentes", "alberfue", "alberfue", null);
		user12.setUserAccount(account12);
		user12 = userRepository.save(user12);

		User user13 = new User("Luisa", "Torres Chacon", "luitorcha", "luitorcha", null);
		user13.setUserAccount(account13);
		user13 = userRepository.save(user13);

		
		entities.put("user12", user12.getId());
		entities.put("user13", user13.getId());
		
		Team team1 = teamRepository.save(new Team("Olimpia"));
		Team team2 = teamRepository.save(new Team("Innovae"));
		Team team3 = teamRepository.save(new Team("DEL5"));
		Team team4 = teamRepository.save(new Team("Fujitsu"));

		//TEAM BASIC
		Team team5 = teamRepository.save(new Team("BasicTeam"));
		//TEAM STANDARD
		Team team6 = teamRepository.save(new Team("StandardTeam"));
		//TEAM BASIC-STANDARD
		Team team7 = teamRepository.save(new Team("BasicStandardTeam"));

		entities.put("team1", team1.getId());
		entities.put("team2", team2.getId());
		entities.put("team3", team3.getId());
		entities.put("team4", team4.getId());
		entities.put("team5", team5.getId());
		entities.put("team6", team6.getId());
		entities.put("team7", team7.getId());

		UserRol rol1 = this.userRolRepository.save(new UserRol(true, user1, team1));
		UserRol rol2 = this.userRolRepository.save(new UserRol(true, user2, team2));
		UserRol rol3 = this.userRolRepository.save(new UserRol(true, user3, team3));
		UserRol rol4 = this.userRolRepository.save(new UserRol(true, user4, team4));
		UserRol rol5 = this.userRolRepository.save(new UserRol(false, user4, team1));

		//USER ROLES TEAM BASIC
		UserRol rol6 = this.userRolRepository.save(new UserRol(true, user10, team5));
		UserRol rol7 = this.userRolRepository.save(new UserRol(true, user11, team5));
		
		//USER ROLES TEAM STANDARD
		UserRol rol8 = this.userRolRepository.save(new UserRol(true, user12, team6));
		UserRol rol9 = this.userRolRepository.save(new UserRol(true, user13, team6));
		
		//USER ROLES TEAM BASIC-STANDARD
		UserRol rol10 = this.userRolRepository.save(new UserRol(true, user10, team7));
		UserRol rol11 = this.userRolRepository.save(new UserRol(true, user13, team7));

		entities.put("rol1", rol1.getId());
		entities.put("rol2", rol2.getId());
		entities.put("rol3", rol3.getId());
		entities.put("rol4", rol4.getId());
		entities.put("rol5", rol5.getId());
		entities.put("rol6", rol6.getId());
		entities.put("rol7", rol7.getId());
		entities.put("rol8", rol8.getId());
		entities.put("rol9", rol9.getId());
		entities.put("rol10", rol10.getId());
		entities.put("rol11", rol11.getId());
		
		LocalDateTime localDateTime0 = LocalDateTime.of(0, 2, 03, 10, 15);
		Date localDate0 = Date.from(localDateTime0.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime00 = LocalDateTime.of(9999, 2, 03, 10, 15);
		Date localDate00 = Date.from(localDateTime00.atZone(ZoneId.systemDefault()).toInstant());

		LocalDateTime localDateTime1 = LocalDateTime.of(2020, 2, 03, 10, 15);
		Date localDate1 = Date.from(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime2 = LocalDateTime.of(2020, 2, 13, 10, 15);
		Date localDate2 = Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime3 = LocalDateTime.of(2020, 12, 24, 10, 15);
		Date localDate3 = Date.from(localDateTime3.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime4 = LocalDateTime.of(2020, 1, 05, 10, 15);
		Date localDate4 = Date.from(localDateTime4.atZone(ZoneId.systemDefault()).toInstant());

		Invitation invitation1 = this.invitationRepository
				.save(new Invitation("Buenas tardes, le invito a pertenecer a nuestro equipo Olimpia", localDate1, true,
						user1, user4, team1));
		Invitation invitation2 = this.invitationRepository.save(
				new Invitation("Hola, necesitamos sus servicios en Olimpia", localDate2, false, user1, user3, team1));
		Invitation invitation3 = this.invitationRepository.save(new Invitation(
				"Hola, su perfil nos sería de gran ayuda en nuestro equipo", localDate3, null, user1, user2, team1));
		Invitation invitation4 = this.invitationRepository
				.save(new Invitation("Buenas tardes, sabemos que rechazó nuestra invitación pero le necesitamos",
						localDate4, null, user1, user3, team1));

		entities.put("invitation1", invitation1.getId());
		entities.put("invitation2", invitation2.getId());
		entities.put("invitation3", invitation3.getId());
		entities.put("invitation4", invitation4.getId());

		Project project1 = projectRepository.save(new Project("Scrume",
				"Proyecto dedicado a la creación de una plataforma para la aplicación de scrum en proyectos", team1));
		Project project2 = projectRepository.save(new Project("Hackathon",
				"Proyecto dedicado a aprender a utilizar la tecnología que usamos en la empresa", team2));
		Project project3 = projectRepository.save(new Project("Acme-Handy-Worker",
				"Proyecto dedicado a la gestión de trabajos puntuales, como arreglos de averías", team3));
		Project project4 = projectRepository.save(new Project("PureEmotionBox",
				"Proyecto dedicado a la creación de cajas sorpresa, de diferentes temáticas", team4));
		Project project5 = projectRepository.save(new Project("Acme-Writers",
				"Proyecto dedicado a la creación de una plataforma para la publicación de libros de autores poco conocidos gracias a editoriales",
				team1));
		Project project6 = projectRepository.save(new Project("Acme-Basic",
				"Proyecto de nuestro equipo basico",
				team5));
		Project project7 = projectRepository.save(new Project("Acme-Standard",
				"Proyecto de nuestro equipo standard",
				team6));

		entities.put("project1", project1.getId());
		entities.put("project2", project2.getId());
		entities.put("project3", project3.getId());
		entities.put("project4", project4.getId());
		entities.put("project5", project5.getId());
		entities.put("project6", project6.getId());
		entities.put("project7", project7.getId());


		LocalDateTime localDateTime5 = LocalDateTime.of(2020, 3, 25, 10, 15);
		Date localDate5 = Date.from(localDateTime5.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime6 = LocalDateTime.of(2020, 8, 24, 23, 59);
		Date localDate6 = Date.from(localDateTime6.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime7 = LocalDateTime.of(2020, 10, 25, 10, 15);
		Date localDate7 = Date.from(localDateTime7.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime8 = LocalDateTime.of(2020, 11, 05, 10, 15);
		Date localDate8 = Date.from(localDateTime8.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime9 = LocalDateTime.of(2020, 7, 25, 10, 15);
		Date localDate9 = Date.from(localDateTime9.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime10 = LocalDateTime.of(2020, 8, 25, 10, 15);
		Date localDate10 = Date.from(localDateTime10.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime11 = LocalDateTime.of(2020, 8, 25, 10, 15);
		Date localDate11 = Date.from(localDateTime11.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime12 = LocalDateTime.of(2020, 9, 25, 10, 15);
		Date localDate12 = Date.from(localDateTime12.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime13 = LocalDateTime.of(2020, 8, 25, 10, 15);
		Date localDate13 = Date.from(localDateTime13.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime14 = LocalDateTime.of(2020, 12, 25, 10, 15);
		Date localDate14 = Date.from(localDateTime14.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTime15 = LocalDateTime.of(2020, 8, 26, 10, 15);
		Date localDate15 = Date.from(localDateTime15.atZone(ZoneId.systemDefault()).toInstant());
		
		
		Sprint sprint1 = this.sprintRepository.save(new Sprint(localDate5, localDate6, project1));
		Sprint sprint2 = this.sprintRepository.save(new Sprint(localDate7, localDate8, project2));
		Sprint sprint3 = this.sprintRepository.save(new Sprint(localDate9, localDate10, project3));
		Sprint sprint4 = this.sprintRepository.save(new Sprint(localDate11, localDate12, project4));
		Sprint sprint5 = this.sprintRepository.save(new Sprint(localDate13, localDate14, project1));
		Sprint sprint6 = this.sprintRepository.save(new Sprint(localDate0, localDate00, project6));
		Sprint sprint7 = this.sprintRepository.save(new Sprint(localDate0, localDate00, project7));

		entities.put("sprint1", sprint1.getId());
		entities.put("sprint2", sprint2.getId());
		entities.put("sprint3", sprint3.getId());
		entities.put("sprint4", sprint4.getId());
		entities.put("sprint5", sprint5.getId());
		entities.put("sprint6", sprint6.getId());
		entities.put("sprint7", sprint7.getId());


		Workspace workspace1 = this.workspaceRepository.save(new Workspace("Fase de planificación", sprint1));
		Workspace workspace2 = this.workspaceRepository.save(new Workspace("Tareas de formación", sprint2));
		Workspace workspace3 = this.workspaceRepository
				.save(new Workspace("Tareas de análisis de requisitos", sprint3));
		Workspace workspace4 = this.workspaceRepository.save(new Workspace("Fase de cierre", sprint4));
		Workspace workspace5 = this.workspaceRepository.save(new Workspace("Default", sprint5));

		Workspace workspace6 = this.workspaceRepository.save(new Workspace("Default", sprint6));
		Workspace workspace7 = this.workspaceRepository.save(new Workspace("Default", sprint7));
		Workspace workspace8 = this.workspaceRepository.save(new Workspace("Fase de desarrollo", sprint1));
		
		entities.put("workspace1", workspace1.getId());
		entities.put("workspace2", workspace2.getId());
		entities.put("workspace3", workspace3.getId());
		entities.put("workspace4", workspace4.getId());
		entities.put("workspace5", workspace5.getId());
		entities.put("workspace6", workspace6.getId());
		entities.put("workspace7", workspace7.getId());
		entities.put("workspace8", workspace8.getId());


		String toDoName = "To do";
		String inProgressName = "In progress";
		String doneName = "Done";

		Column toDo1 = this.columnRepository.save(new Column(toDoName, workspace1));
		Column inProgress1 = this.columnRepository.save(new Column(inProgressName, workspace1));
		Column done1 = this.columnRepository.save(new Column(doneName, workspace1));

		Column toDo2 = this.columnRepository.save(new Column(toDoName, workspace2));
		Column inProgress2 = this.columnRepository.save(new Column(inProgressName, workspace2));
		Column done2 = this.columnRepository.save(new Column(doneName, workspace2));

		Column toDo3 = this.columnRepository.save(new Column(toDoName, workspace3));
		Column inProgress3 = this.columnRepository.save(new Column(inProgressName, workspace3));
		Column done3 = this.columnRepository.save(new Column(doneName, workspace3));

		Column toDo4 = this.columnRepository.save(new Column(toDoName, workspace4));
		Column inProgress4 = this.columnRepository.save(new Column(inProgressName, workspace4));
		Column done4 = this.columnRepository.save(new Column(doneName, workspace4));

		Column toDo5 = this.columnRepository.save(new Column(toDoName, workspace5));
		Column inProgress5 = this.columnRepository.save(new Column(inProgressName, workspace5));
		Column done5 = this.columnRepository.save(new Column(doneName, workspace5));
		
		Column toDo6 = this.columnRepository.save(new Column(toDoName, workspace6));
		Column inProgress6 = this.columnRepository.save(new Column(inProgressName, workspace6));
		Column done6 = this.columnRepository.save(new Column(doneName, workspace6));
		
		Column toDo7 = this.columnRepository.save(new Column(toDoName, workspace7));
		Column inProgress7 = this.columnRepository.save(new Column(inProgressName, workspace7));
		Column done7 = this.columnRepository.save(new Column(doneName, workspace7));
		
		Column toDo8 = this.columnRepository.save(new Column(toDoName, workspace8));
		Column inProgress8 = this.columnRepository.save(new Column(inProgressName, workspace8));
		Column done8 = this.columnRepository.save(new Column(doneName, workspace8));

		entities.put("toDo", toDo1.getId());
		entities.put("inProgress", inProgress1.getId());
		entities.put("done", done1.getId());

		entities.put("toDo2", toDo2.getId());
		entities.put("inProgress2", inProgress2.getId());
		entities.put("done2", done2.getId());

		entities.put("toDo3", toDo3.getId());
		entities.put("inProgress3", inProgress3.getId());
		entities.put("done3", done3.getId());

		entities.put("toDo4", toDo4.getId());
		entities.put("inProgress4", inProgress4.getId());
		entities.put("done4", done4.getId());

		entities.put("toDo5", toDo5.getId());
		entities.put("inProgress5", inProgress5.getId());
		entities.put("done5", done5.getId());
		
		entities.put("toDo6", toDo6.getId());
		entities.put("inProgress6", inProgress6.getId());
		entities.put("done6", done6.getId());
		
		entities.put("toDo7", toDo7.getId());
		entities.put("inProgress7", inProgress7.getId());
		entities.put("done7", done7.getId());
		
		entities.put("toDo8", toDo8.getId());
		entities.put("inProgress8", inProgress8.getId());
		entities.put("done8", done8.getId());

		Set<User> list1 = new HashSet<>();
		list1.add(user1);
		list1.add(user4);

		Set<User> list2 = new HashSet<>();
		list2.add(user1);

		Set<User> list3 = new HashSet<>();
		list3.add(user4);

		Task task1 = this.taskRepository.save(new Task("Definición del producto",
				"Se deberá definir el caso de uso core y y el mínimo producto viable.", 10, project1, list1, done1));
		Task task2 = this.taskRepository.save(new Task("Análisis de competidores",
				"Se deberá estudiar el mercado actual, observando los principales competidores de nuestro producto.", 8,
				project1, list1, inProgress1));
		Task task3 = this.taskRepository.save(new Task("Métricas de rendimiento",
				"Se deben definir las métricas que determinarán el rendimiento de cada componente del grupo.", 18,
				project1, list3, toDo5));
		Task task4 = this.taskRepository.save(new Task("CU2-Equipo",
				"Un equipo está formado por un nombre y usuarios que tengan el mismo paquete, además todos los usuarios se pueden salir del equipo, siempre que haya al menos un administrador.",
				0, project1, list3, null));
		Task task5 = this.taskRepository.save(new Task("CU4-Sprint",
				"Un sprint está formado por fecha de inicio, fecha de fin y un proyecto asociado. Un administrador puede crear y editar sprints.",
				0, project1, list2, null));
		Task task6 = this.taskRepository.save(new Task("CU3-Proyecto",
				"Un proyecto está formado por nombre, descripción, product backlog y el equipo que lo gestiona.", 0,
				project1, list1, null));

		entities.put("task1", task1.getId());
		entities.put("task2", task2.getId());
		entities.put("task3", task3.getId());
		entities.put("task4", task4.getId());
		entities.put("task5", task5.getId());
		entities.put("task6", task6.getId());

		
		Document doc1 = this.documentRepository.save(new Document(DocumentType.DAILY, "Daily 17/04/2020",
				"[{\"name\": \"testUser4\", \"done\": \"Terminar populate\", \"doing\": \"Empezar mi primer caso de uso\", \"problems\": \"No se usar spring boot\"}]",
				sprint1, false));
		Document doc2 = this.documentRepository.save(new Document(DocumentType.DAILY, "Daily 18/04/2020",
				"[]",
				sprint1, false));
		Document doc3 = this.documentRepository.save(new Document(DocumentType.REVIEW, "Review",
				"{\"done\": \"PDF\", \"noDone\": \"Modificar populate\", \"rePlanning\": \"Graficas\"}", sprint1,
				true));
		Document doc4 = this.documentRepository.save(new Document(DocumentType.RETROSPECTIVE, "Retrospective",
				"{\"good\": \"Sincronización entre entornos\", \"bad\": \"No ha habido comunicacion entre documentacion y presentacion\", \"improvement\": \"Mas reuniones para motivar y sincronizar cambios entre documentacion y presentacion\"}",
				sprint1, true));

		entities.put("doc1", doc1.getId());
		entities.put("doc2", doc2.getId());
		entities.put("doc3", doc3.getId());
		entities.put("doc4", doc4.getId());

		HistoryTask historyTask1 = this.historyTaskRepository
				.save(new HistoryTask(localDateTime5, toDo1, inProgress1, task2));
		HistoryTask historyTask2 = this.historyTaskRepository
				.save(new HistoryTask(localDateTime5, toDo1, toDo5, task3));
		HistoryTask historyTask3 = this.historyTaskRepository.save(new HistoryTask(LocalDateTime.of(2020, 03, 27, 12, 00), inProgress1, done1, task1));
		entities.put("historyTask1", historyTask1.getId());
		entities.put("historyTask2", historyTask2.getId());
		entities.put("historyTask3", historyTask3.getId());

		Estimation estimation1 = this.estimationRepository.save(new Estimation(5, user1, task1));
		Estimation estimation2 = this.estimationRepository.save(new Estimation(15, user4, task1));
		Estimation estimation3 = this.estimationRepository.save(new Estimation(4, user1, task2));
		Estimation estimation4 = this.estimationRepository.save(new Estimation(12, user4, task2));
		Estimation estimation5 = this.estimationRepository.save(new Estimation(18, user1, task3));
		Estimation estimation6 = this.estimationRepository.save(new Estimation(18, user4, task3));
		Estimation estimation7 = this.estimationRepository.save(new Estimation(10, user4, task4));
		Estimation estimation8 = this.estimationRepository.save(new Estimation(11, user4, task5));
		Estimation estimation9 = this.estimationRepository.save(new Estimation(14, user4, task6));

		entities.put("estimation1", estimation1.getId());
		entities.put("estimation2", estimation2.getId());
		entities.put("estimation3", estimation3.getId());
		entities.put("estimation4", estimation4.getId());
		entities.put("estimation5", estimation5.getId());
		entities.put("estimation6", estimation6.getId());
		entities.put("estimation7", estimation7.getId());
		entities.put("estimation8", estimation8.getId());
		entities.put("estimation9", estimation9.getId());
		Payment payment0 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user0.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234516", "ABC123456781A"));
		Payment payment1 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user1.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234526", "ABC123456782A"));
		Payment payment2 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user1.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234536", "ABC123456783A"));
		Payment payment3 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user4.getUserAccount(), LocalDate.from(localDateTime1), "ABC1234546", "ABC123456784A"));
		Payment payment4 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime1), proBox,
				user4.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234556", "ABC123456785A"));
		Payment payment5 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user2.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234566", "ABC123456786A"));
		Payment payment6 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user3.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234577", "ABC123456787A"));

		entities.put("payment0", payment0.getId());
		entities.put("payment1", payment1.getId());
		entities.put("payment2", payment2.getId());
		entities.put("payment3", payment3.getId());
		entities.put("payment4", payment4.getId());
		entities.put("payment5", payment5.getId());
		entities.put("payment6", payment6.getId());
		
		//PAYMENTS CADUCADOS
		Payment payment7 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user5.getUserAccount(), LocalDate.from(localDateTime0), "ABC1234561B", "ABC123456781C"));
		Payment payment8 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user6.getUserAccount(), LocalDate.from(localDateTime0), "ABC1234562B", "ABC123456782C"));
		Payment payment9 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user7.getUserAccount(), LocalDate.from(localDateTime0), "ABC1234563B", "ABC123456783C"));
		Payment payment10 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user8.getUserAccount(), LocalDate.from(localDateTime0), "ABC1234564B", "ABC123456784C"));
		Payment payment11 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), proBox,
				user9.getUserAccount(), LocalDate.from(localDateTime0), "ABC1234565B", "ABC123456785C"));
		
		//PAYMENTS BASIC
		Payment payment12 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), basicBox,
				user10.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234561Z", "ABC123456781Y"));
		Payment payment13 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), basicBox,
				user11.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234562Z", "ABC123456782Y"));
		
		//PAYMENTS STANDARD
		Payment payment14 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), standardBox,
				user12.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234561D", "ABC123456781X"));
		Payment payment15 = this.paymentRepository.save(new Payment(LocalDate.from(localDateTime0), standardBox,
				user13.getUserAccount(), LocalDate.from(localDateTime00), "ABC1234562D", "ABC123456782X"));	
		
		entities.put("payment7", payment7.getId());
		entities.put("payment8", payment8.getId());
		entities.put("payment9", payment9.getId());
		entities.put("payment10", payment10.getId());
		entities.put("payment11", payment11.getId());
		entities.put("payment12", payment12.getId());
		entities.put("payment13", payment13.getId());
		entities.put("payment14", payment14.getId());
		entities.put("payment15", payment15.getId());

		Note note1 = this.noteRepository.save(new Note(user1, "Revisar mis tareas"));
		Note note2 = this.noteRepository.save(new Note(user1, "Hablar con el coordinador"));
		Note note3 = this.noteRepository.save(new Note(user1, "Mirar documentación del error en mi tarea"));
		Note note4 = this.noteRepository.save(new Note(user1, "Avisar al equipo de mi problema"));

		entities.put("note1", note1.getId());
		entities.put("note2", note2.getId());
		entities.put("note3", note3.getId());
		entities.put("note4", note4.getId());
		
		LocalDateTime localDateTime17 = LocalDateTime.of(2020, 4, 18, 00, 00);
		Date localDate17 = Date.from(localDateTime17.atZone(ZoneId.systemDefault()).toInstant());
		
		
		Notification notification1 = this.notificationRepository.save(new Notification("Realizar sprint planning meeting", localDate15, sprint5, null));
		Notification notification2 = this.notificationRepository.save(new Notification("Debes rellenar la daily de hoy (18/04/2020)", localDate17, sprint1, user1));
		Notification notification3 = this.notificationRepository.save(new Notification("Debes rellenar la daily de hoy (18/04/2020)", localDate17, sprint1, user4));

		
		entities.put("notification1", notification1.getId());
		entities.put("notification2", notification2.getId());
		entities.put("notification3", notification3.getId());

		SecurityBreach securityBreach = this.securityBreachRepository.save(new SecurityBreach("Hemos encontrado una brecha de seguridad en el sistema, disculpe las molestias.", false));
		entities.put("securityBreach", securityBreach.getId());

		
		Utiles.escribeFichero(entities, properties);

		log.info("The entities mapped are: \n" + entities.keySet().stream().map(x -> {
			Integer value = entities.get(x);
			return x + "=" + value + "\n";
		}).collect(Collectors.joining()));

		securityBreachRepository.flush();
		userRolRepository.flush();
		columnRepository.flush();
		workspaceRepository.flush();
		taskRepository.flush();
		sprintRepository.flush();
		projectRepository.flush();
		teamRepository.flush();
		userRepository.flush();
		boxRepository.flush();
		accountRepository.flush();
		invitationRepository.flush();
		paymentRepository.flush();
		historyTaskRepository.flush();
		estimationRepository.flush();
		documentRepository.flush();
		noteRepository.flush();
		notificationRepository.flush();
	}

}
