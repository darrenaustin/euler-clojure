# euler-clojure

My solutions to the [Project Euler][1] problems written in the
[Clojure][2] programming language.  I am doing this for fun and
profit.  But mostly fun.  It is a great way to learn a new programming
language and excercise some math muscles (although some of the math
involved was new to me).

Given that, these are just my solutions to the problems.  There may be
cleaner, or more idiomattic ways to do this in Clojure (if so, please
drop me a line and let me know).  I am learning as I go, so some of
the early solutions might be a little off.

## Spoiler warning

It should go without saying that if you are trying to solve these
problems yourself, looking at my solutions may spoil it for you.  Feel
free to use these solutions if you wish... only cheating yourself,
blah, blah.

## Usage

Currently this project requires Clojure 1.2.  I will update it to 1.3
sometime soon.  I use `lein swank` to get a REPL up and running with the 
right environment setup.  To run the solutions:

    user> (use 'dma.euler)
    user> (euler)
  
This will run all of the solutions giving the result, whether it was correct
and how long it took (with a warning if it was over a minute... gotta keep
myself honest :).

You can also run just a specific problem by giving the problem number
to the `euler` function:

    user> (euler 42)
    

## TODO list
* Migrate to 1.4
* Figure out why 87 is now running out of memory.
* Clean up numeric.clj
* Clean up :use clauses to only specify exactly what is being used in
  each file
* Solve the rest of the problems :)

## Problems to migrate from Ruby project

I originally started with Project Euler as a way to learn Ruby.  I
have migrated most of my original solutions over to Clojure.  Although
in many cases the Clojure solution is very different from how I solved
it in Ruby.  Here is my migration TODO list.  Problems I have solved
in Ruby, but not yet in Clojure:

* 96
* 111
* 114
* 115
* 116
* 117
* 124

[1]: http://projecteuler.net
[2]: http://clojure.org
