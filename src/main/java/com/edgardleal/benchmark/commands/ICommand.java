package com.edgardleal.benchmark.commands;

/**
 * <p> </p>
 *
 * @author Edgard Leal <edgardleal@gmail.com>
 * @since 5/12/2017 10:04 AM
 * @version $Id: $Id
 */
public interface ICommand {

  /**
   * <p>execute.</p>
   *
   * @param command a {@link java.lang.String} object.
   * @param args a {@link java.lang.String} object.
   * @return a boolean.
   */
  boolean execute(final String command, final String... args);
}
