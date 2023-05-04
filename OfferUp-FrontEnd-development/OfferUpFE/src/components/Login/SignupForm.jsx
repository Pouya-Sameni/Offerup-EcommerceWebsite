import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { signup } from "../../services/Accounts";
import { useState } from "react";
import InputMask from "react-input-mask";

const style = {
  container: "flex items-center justify-center min-h-screen",
  container2: "flex items-center justify-center border-4 bg-white",
  card: "max-w-420px p-20 bg-cream",
  link: "hover:text-black",
  copyright: "text-gray hover:text-black",
  signIn: "text-green text-lg py-4",
  box: "mt-8 flex flex-col items-center",
};

const theme = createTheme();

export default function SignUp() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [postalCode, setPostalCode] = useState("");
  const [country, setCountry] = useState("");
  const [city, setCity] = useState("");
  const [province, setProvince] = useState("");
  const [username, setUsername] = useState("");

  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (
      firstName.trim() === "" ||
      lastName.trim() === "" ||
      email.trim() === "" || 
      password.trim() === "" ||
      address.trim() === "" ||
      postalCode.trim() === "" ||
      country.trim() === "" ||
      city.trim() === "" ||
      province.trim() === "" ||
      username.trim() === ""
    ) {
      setErrorMessage("All fields are required!");
      return;
    }else if (!email.trim().includes('@')){
      setErrorMessage("Email Not Valid");
    }

    const formData = {
      email: email,
      password: password,
      username: username,
      firstName: firstName,
      lastName: lastName,
      address: {
        address: address,
        country: country,
        postalCode: postalCode,
        province: province,
        city: city,
      },
    };
    const signInData = await signup(formData);

    if (signInData === "Successfull") {
      window.location.href = "/home";
    } else {
      setErrorMessage("email or username exists");
    }
  };

  return (
    <div className="absolute mt-32 mr-10">
      <ThemeProvider theme={theme}>
        <Container
          component="main"
          maxWidth="xs"
          className={`${style.container2}`}
          style={{ borderRadius: "20px" }}
        >
          <Box className={style.box}>
            <Avatar>
              <LockOutlinedIcon />
            </Avatar>
            <Typography
              className={style.signIn}
              class="text-center"
              style={{ fontSize: "1.5rem" }}
            >
              Sign up
            </Typography>
            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    autoComplete="given-name"
                    name="firstName"
                    required
                    fullWidth
                    id="firstName"
                    label="First Name"
                    autoFocus
                    value={firstName}
                    onChange={(event) => setFirstName(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="lastName"
                    label="Last Name"
                    name="lastName"
                    autoComplete="family-name"
                    value={lastName}
                    onChange={(event) => setLastName(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="userName"
                    label="Username"
                    name="username"
                    autoComplete="username"
                    value={username}
                    onChange={(event) => setUsername(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    inputProps={{
                      pattern: "^\\S+@\\S+$",
                      title: "Enter a valid email address",
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="new-password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="Address"
                    label="Address"
                    type="Address"
                    id="Address"
                    autoComplete="address"
                    value={address}
                    onChange={(event) => setAddress(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="postalcode"
                    label="Postal Code"
                    name="postalcode"
                    autoComplete="postalcode"
                    value={postalCode}
                    onChange={(event) => setPostalCode(event.target.value)}
                    InputProps={{
                      inputComponent: InputMask,
                      inputProps: {
                        mask: "a9a 9a9",
                        maskPlaceholder: "",
                      },
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="country"
                    label="Country"
                    name="country"
                    autoComplete="country"
                    value={country}
                    onChange={(event) => setCountry(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="city"
                    label="City"
                    name="city"
                    autoComplete="city"
                    value={city}
                    onChange={(event) => setCity(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="province"
                    label="Province"
                    name="province"
                    autoComplete="province"
                    value={province}
                    onChange={(event) => setProvince(event.target.value)}
                    inputProps={{
                      maxLength: 30
                    }}
                  />
                </Grid>
              </Grid>
              <Typography variant="subtitle2" color="error" align="center">
                {errorMessage}
              </Typography>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                color="primary"
                style={{ backgroundColor: "#000000", color: "#ffffff" }}
              >
                Sign Up
              </Button>

              <Grid
                container
                justifyContent="center"
                alignItems="center"
                padding="10px"
              >
                <Grid item>
                  <Link className={style.link} href="/">
                    Already have an account? Sign in
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
}
