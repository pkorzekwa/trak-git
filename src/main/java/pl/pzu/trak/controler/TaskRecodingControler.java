package pl.pzu.trak.controler;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pzu.trak.domain.Task;
import pl.pzu.trak.domain.TaskRecoding;
import pl.pzu.trak.services.TaskRecodingService;
import pl.pzu.trak.services.TaskService;

@Controller
@RequestMapping(value = "/taskrecoding")

public class TaskRecodingControler
{
	@Autowired
	private TaskRecodingService taskRecodingService;
	
	@Autowired
	private TaskService taskService;


	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	@ResponseBody
	public String taskAndTasksRecoding()
	{
		StringBuilder response = new StringBuilder();
		for (Task task : taskService.findAll())
		{
			response.append(task).append("<br>");
			for (TaskRecoding taskRecoding : task.getTasksRecoding())
			{
				response.append("- ").append(taskRecoding).append("<br>");
			}
		}
		return response.toString();
	}

	@RequestMapping(value = "/add/{taskId}", method = RequestMethod.GET)
	public String addTaskRecoding(Model model, @PathVariable(value = "taskId") Short taskId)
	{
		TaskRecoding taskRecoding = new TaskRecoding();
		model.addAttribute("newTaskRecoding", taskRecoding);
		return "user/task/addTaskRecoding";
	}

	
	@RequestMapping(value = "/add/{taskId}", params = { "save" }, method = RequestMethod.POST)
	public String addTaskRecoding(@Valid @ModelAttribute("newTaskRecoding") TaskRecoding taskRecoding,
			BindingResult bindingResult, RedirectAttributes attributes, Model model, @PathVariable(value = "taskId") Short taskId)
	{
		if (bindingResult.hasErrors())
		{
			return "addTaskRecoding";
		} else
		{
			Task task = taskService.findOne(taskId);			
			taskRecoding.withTask(task);
			task.getTasksRecoding().add(taskRecoding);
			taskRecodingService.save(taskRecoding);
			return "redirect:/tasks/edit/{taskId}";
		}
	}


	@RequestMapping(value = "/edit/{taskId}/{taskRecodingId}", method = RequestMethod.GET)
	public String editTaskRecoding(Model model, @PathVariable(value = "taskId") Short taskId, @PathVariable(value = "taskRecodingId") Integer taskRecodingId)
	{
		model.addAttribute("taskRecoding", taskRecodingService.findOne(taskRecodingId));
		return "user/task/editTaskRecoding";
	}

	
	@RequestMapping(value = "/edit/{taskId}/{taskRecodingId}", params = { "save" }, method = RequestMethod.POST)
	public String updateTaskRecoding(@Valid @ModelAttribute("taskRecoding") TaskRecoding taskRecoding,
			BindingResult bindingResult, RedirectAttributes attributes, Model model, @PathVariable(value = "taskId") Short taskId, @PathVariable(value = "taskRecodingId") Integer taskRecodingId)
	{
		if (bindingResult.hasErrors())
		{
			return "editTaskRecoding";
		} else
		{
			Task task = taskService.findOne(taskId);
			taskRecoding.withTask(task);
			task.getTasksRecoding().add(taskRecoding);					
			taskRecodingService.save(taskRecoding);
			return "redirect:/tasks/edit/{taskId}";
		}
	}

}
