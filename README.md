# Running the application:
```
java -jar smart-xml-analyzer.jar <input_origin_file_path> <input_other_sample_file_path> <target_element_id>
```

# Example output:
```
html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]
Element tag name match: a
Element class name match: btn
Element class name match: btn-success
Element attribute match: href = #ok
Element attribute match: title = Make-Button
Element attribute match: rel = next
Total match score: 6
```