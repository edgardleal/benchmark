#!/bin/bash

declare -r classes="target/classes"
declare -r dependencies="target/dependency/"
declare path_separator=":"

if [ ! "$(uname -s)" == "Linux" ]; then
  path_separator=";"
fi

if [ ! -d "$classes" ]; then
  mvn compile
fi

if [ ! -d "$dependencies" ]; then
  mvn "dependency:copy-dependencies"
fi

declare parameters="$@"
[ -z "$parameters" ] && parameters="RegexString" 

java -cp "${classes}${path_separator}${dependencies}*" -client com.edgardleal.benchmark.Benchmark $parameters
