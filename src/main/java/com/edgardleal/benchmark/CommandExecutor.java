package com.edgardleal.benchmark;

import com.edgardleal.benchmark.commands.ICommand;
import org.apache.commons.lang3.StringUtils;

/**
 * <p> </p>
 *
 * @author Edgard Leal <edgardleal@gmail.com>
 * @since 5/12/2017 10:07 AM
 * @version $Id: $Id
 */
public class CommandExecutor {

  /**
   * <p>execute.</p>
   *
   * @param command a {@link java.lang.String} object.
   * @param args a {@link java.lang.String} object.
   */
  public void execute(String command, String... args){
    final String className = String.format("com.edgardleal.benchmark.commands.%s", StringUtils.capitalize(command));
    try {
      final ICommand runnableCommand = (ICommand) Class.forName(className).newInstance();
      runnableCommand.execute(command, args);
    } catch (ClassNotFoundException e) {
      System.out.println("Command not found: " + command);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
  }
}
