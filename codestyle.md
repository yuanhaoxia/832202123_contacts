# Code Style Guide
Follow Alibaba Java Coding Guidelines (source: <https://github.com/alibaba/Alibaba-Java-Coding-Guidelines>)



## Naming Conventions

1. **Name Rules**:
   - Names must not start or end with an underscore or dollar sign.
   - Use meaningful and descriptive English names; avoid Chinese, Pinyin, or mixed-language names to enhance clarity.

2. **Class Names**:
   - Use UpperCamelCase for class names, ensuring they are nouns, e.g., `UserProfile`, `OrderManager`.

3. **Method and Variable Names**:
   - Use lowerCamelCase for method and variable names, ensuring they are descriptive of their functionality, e.g., `getUserId`, `calculateTotal`.

4. **Constants**:
   - Use uppercase letters with underscores for constant names, making sure they are semantically complete, e.g., `MAX_CONNECTIONS`, `DEFAULT_TIMEOUT`.

5. **Boolean Variables**:
   - Avoid starting boolean variable names with `is` to prevent serialization issues. Use clear names like `success` or `hasErrors`, e.g., `boolean success;`.

6. **Package Names**:
   - Use lowercase letters and singular form for package names to maintain consistency and clarity, e.g., `com.example.project`.

## Formatting Requirements

1. **Braces**:
   - Place the opening brace on the same line as the control statement or method declaration. Place the closing brace on a new line. Example:
     ```java
     if (condition) {
         // code block
     }
     ```

2. **Space Usage**:
   - Use one space between keywords (like `if`, `for`, etc.) and their opening parentheses. Ensure there is one space on both sides of operators for readability.

3. **Indentation**:
   - Use four spaces for indentation throughout your code. Do not use tab characters to ensure consistent formatting.

4. **Line Length**:
   - Limit lines to a maximum of 120 characters. If a line exceeds this limit, break it into multiple lines following logical breaks (e.g., after operators or commas).

5. **Blank Lines**:
   - Use a single blank line to separate sections of code that represent different logical components. This enhances readability without clutter.

## Constants and Comments

1. **Constants**:
   - Avoid using magic values directly in your code. Define all constants in dedicated classes to improve maintainability and clarity.

2. **Comments**:
   - Provide clear and concise comments for key methods and any complex logic within your code. This helps others (and yourself) understand your code's purpose and functionality in the future.


