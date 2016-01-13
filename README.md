# json-transformer
Simple JSON transformer from Disqus format to Pig JSON format


Reads all files in the given directory where each file is a array of JSON objects (the
output of Disqus API) and merges them into one file, where each entry is on a single
line (Pig JSON format).

Usage:

```
$java -jar target/json-transformer-1.0-SNAPSHOT.jar inputDirWithSeveralJsonFiles outputFile.json
```

See ```JSONDisqusToPigTransformerTest``` for details.