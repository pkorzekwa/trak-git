package pl.pzu.trak.controler;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.pzu.trak.domain.Syst;
import pl.pzu.trak.domain.Task;
import pl.pzu.trak.services.SystService;
import pl.pzu.trak.services.TaskService;

@Controller
@RequestMapping(value = "/tasks")
public class TaskController
{

	//public static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;
	@Autowired
	private SystService systService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Map<String, Object> model)
	{
		model.put("taskList", taskService.findAll());
		return "user/task/tasks";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewTaskForm(Model model)
	{
		Task newTask = new Task();
		model.addAttribute("newTask", newTask);
		List<Syst> systs = systService.findAll();
		model.addAttribute("systList", systs);
		return "user/task/addTask";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewTask(@ModelAttribute("newTask") Task task, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			return "addTask";
		} else
		{
			taskService.add(task);
			return "redirect:/tasks/all";
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable(value = "id") Short taskId)
	{
		taskService.remove(taskId);
		return "redirect:/tasks/all";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editTask(Model model, @PathVariable(value = "id") Short taskId)
	{
		model.addAttribute("task", taskService.getTaskByIdWithDetail(taskId));
		List<Syst> systs = systService.findAll();
		model.addAttribute("systList", systs);
		return "user/task/editTask";
	}


	@RequestMapping(value = "/edit/{id}", params = { "save" }, method = RequestMethod.POST)
	public String updateTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, RedirectAttributes attributes, Model model)
	{
		if (bindingResult.hasErrors())
		{
			List<Syst> systs = systService.findAll();
			model.addAttribute("systList", systs);						
			return "editTask";
		} else
		{
			
			taskService.save(task);
			return "redirect:/tasks/all";
		}
	}	
}