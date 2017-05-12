package com.edgardleal.benchmark.commands;

import static com.edgardleal.benchmark.Version.VERSION;

/**
 * <p>This class create a command to print current version on output. </p>
 *
 * @author Edgard Leal <edgardleal@gmail.com>
 * @since 5/12/2017 10:06 AM
 * @version $Id: $Id
 */
public class Version implements ICommand {

  /** {@inheritDoc} */
  @Override
  public boolean execute(String command, String... args) {
    System.out.println(String.format("Version: %s", VERSION));
    return false;
  }
}
