package com.mythos;

import java.util.*;

public class App 
{
    public static void main(String[] args)
    {
		try {
			Args arg = new Args("l,p#,d*,n##", args);

			boolean logging = arg.get('l');
			int port = arg.get('p');
			String directory = arg.get('d');
			List<Integer> numbers = arg.get('n');

			System.out.println("logging: " + (logging ? "true" : "false"));
			System.out.println("port: " + port);
			System.out.println("directory: " + directory);

			if (arg.has('n')) {
				System.out.println("Numbers...");
				for (int i = 0; i < numbers.size(); i++)
					System.out.printf("%d%c", numbers.get(i), (i + 1) % 5 == 0 ? '\n' : '\t');

				System.out.println();
			}

		} catch (ArgsException e) {
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
    }
}
