#!/bin/bash

declare -r classes="target/classes"
declare -r dependencies="target/dependency/"
declare commands=""

if [ ! -d "$classes" ]; then
  commands="$commands compile"
fi

if [ ! -d "$dependencies" ]; then
  commands="$commands compile dependency:copy-dependencies"
fi

[ ! -z "$commands" ] && mvn "$commands"

for i in {1,2,3} 
do
  java -cp "${classes}:${dependencies}*" -client com.edgardleal.benchmark.Benchmark $i
done
