<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Student Registration Form</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f5f5f5;
    }

    .form-container {
      background-color: #e8f0ff;
      border: 2px solid #4a90e2;
      padding: 20px;
      max-width: 700px;
      margin: 0 auto;
      border-radius: 5px;
    }

    .form-title {
      text-align: center;
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 20px;
      color: #333;
    }

    .form-row {
      display: flex;
      gap: 15px;
      margin-bottom: 15px;
      align-items: center;
    }

    .form-group {
      flex: 1;
    }

    .form-group.full-width {
      flex: 2;
    }

    label {
      display: block;
      font-size: 12px;
      color: #666;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="email"],
    input[type="date"],
    input[type="tel"],
    select,
    textarea {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 3px;
      font-size: 14px;
      box-sizing: border-box;
    }

    textarea {
      height: 80px;
      resize: vertical;
    }

    .radio-group {
      display: flex;
      gap: 15px;
      align-items: center;
      margin-top: 5px;
    }

    .radio-group input[type="radio"] {
      width: auto;
      margin-right: 5px;
    }

    .checkbox-group {
      display: flex;
      gap: 15px;
      flex-wrap: wrap;
      margin-top: 5px;
    }

    .checkbox-group input[type="checkbox"] {
      width: auto;
      margin-right: 5px;
    }

    .checkbox-item {
      display: flex;
      align-items: center;
    }

    .submit-row {
      text-align: center;
      margin-top: 30px;
    }

    .submit-btn {
      background-color: #4a90e2;
      color: white;
      padding: 10px 30px;
      border: none;
      border-radius: 3px;
      font-size: 16px;
      cursor: pointer;
    }

    .submit-btn:hover {
      background-color: #357abd;
    }
  </style>
</head>
<body>
<div class="form-container">
  <div class="form-title">Student Registration Form</div>

  <form action="student" method="get">
    <!-- Name Row -->
    <div class="form-row">
      <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" required>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" required>
      </div>
    </div>

    <!-- Date of Birth Row -->
    <div class="form-row">
      <div class="form-group">
        <label for="dateOfBirth">Date of Birth</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required>
      </div>
      <div class="form-group">
        <!-- Empty space to maintain layout -->
      </div>
    </div>

    <!-- Email and Mobile Row -->
    <div class="form-row">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="mobile">Mobile</label>
        <input type="tel" id="mobile" name="mobile" required>
      </div>
    </div>

    <!-- Gender Row -->
    <div class="form-row">
      <div class="form-group full-width">
        <label>Gender</label>
        <div class="radio-group">
          <div class="checkbox-item">
            <input type="radio" id="male" name="gender" value="male" required>
            <label for="male">Male</label>
          </div>
          <div class="checkbox-item">
            <input type="radio" id="female" name="gender" value="female" required>
            <label for="female">Female</label>
          </div>
        </div>
      </div>
    </div>

    <!-- Address Row -->
    <div class="form-row">
      <div class="form-group full-width">
        <label for="address">Address</label>
        <textarea id="address" name="address" required></textarea>
      </div>
    </div>

    <!-- City and Pin Code Row -->
    <div class="form-row">
      <div class="form-group">
        <label for="city">City</label>
        <input type="text" id="city" name="city" required>
      </div>
      <div class="form-group">
        <label for="pinCode">Pin Code</label>
        <input type="text" id="pinCode" name="pinCode" required>
      </div>
    </div>

    <!-- State and Country Row -->
    <div class="form-row">
      <div class="form-group">
        <label for="state">State</label>
        <input type="text" id="state" name="state" required>
      </div>
      <div class="form-group">
        <label for="country">Country</label>
        <select id="country" name="country" required>
          <option value="">Select Country</option>
          <option value="india" selected>India</option>
          <option value="usa">United States</option>
          <option value="uk">United Kingdom</option>
          <option value="canada">Canada</option>
          <option value="australia">Australia</option>
        </select>
      </div>
    </div>

    <!-- Hobbies Row -->
    <div class="form-row">
      <div class="form-group full-width">
        <label>Hobbies</label>
        <div class="checkbox-group">
          <div class="checkbox-item">
            <input type="checkbox" id="drawing" name="hobbies" value="drawing">
            <label for="drawing">Drawing</label>
          </div>
          <div class="checkbox-item">
            <input type="checkbox" id="singing" name="hobbies" value="singing">
            <label for="singing">Singing</label>
          </div>
          <div class="checkbox-item">
            <input type="checkbox" id="dancing" name="hobbies" value="dancing">
            <label for="dancing">Dancing</label>
          </div>
          <div class="checkbox-item">
            <input type="checkbox" id="sketching" name="hobbies" value="sketching">
            <label for="sketching">Sketching</label>
          </div>
          <div class="checkbox-item">
            <input type="checkbox" id="others" name="hobbies" value="others">
            <label for="others">Others</label>
          </div>
        </div>
      </div>
    </div>

<br>
    <div class="form-row">
      <div class="form-group full-width">
        <label>Course applies for</label>
        <div class="radio-group">
          <div class="checkbox-item">
            <input type="radio" id="bca" name="course" value="male" required>
            <label for="bca">BCA</label>
          </div>
          <div class="checkbox-item">
            <input type="radio" id="b.com" name="course" value="female" required>
            <label for="b.com">B.Com</label>
          </div>
          <div class="checkbox-item">
            <input type="radio" id="b.sc" name="course" value="female" required>
            <label for="b.sc">Sc</label>
          </div>
          <div class="checkbox-item">
            <input type="radio" id="b.a" name="course" value="female" required>
            <label for="b.a">B.A</label>
          </div>
        </div>
      </div>
    </div>


    <!-- Submit Button -->
    <div class="submit-row">
      <button type="submit" class="submit-btn">Register</button>
    </div>
  </form>
</div>
</body>
</html>