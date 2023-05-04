import React from "react";
import Typography from "@mui/material/Typography";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { signin } from "../../services/Accounts";
import { useState, useEffect } from "react";

const style = {
  container: "flex items-center justify-center min-h-screen",
  container2: "flex items-center justify-center border-4 bg-offwhite",
  card: "max-w-420px p-20 bg-cream",
  link: "hover:text-black",
  copyright: "text-gray hover:text-black",
  signIn: "text-green text-lg py-4",
  box: "mt-8 flex flex-col items-center",
};

const theme = createTheme();

export default function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    const loginCred = {
      username: email,
      password: password,
    };
    const signInData = await signin(loginCred);

    if (signInData === "Successfull") {
      window.location.href = "/home";
    } else {
      setErrorMessage("Incorrect email or password");
      // console.log("not working");
    }
  };
  return (
    <div
      style={{
        position: "absolute",
        top: "25%",
        left: "68%",
      }}
    >
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
              style={{ fontSize: "1.5rem", padding: "5%" }}
            >
              Sign in
            </Typography>
            <Box
              component="form"
              onSubmit={handleSubmit}
              noValidate
              sx={{ mt: 1 }}
            >
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Username"
                name="email"
                autoComplete="email"
                autoFocus
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                inputProps={{
                  maxLength: 30
                }}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                inputProps={{
                  maxLength: 30
                }}
              />
              {errorMessage && (
                <Typography variant="body2" color="red" style={{textAlign: "center"}}>
                  {errorMessage}
                </Typography>
              )}
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                color="primary"
                style={{ backgroundColor: "#000000", color: "#ffffff" }}
                onClick={handleSubmit}
              >
                Sign In
              </Button>
              <Grid>
                <Grid>
                  <div className="flex justify-center">
                    <Link
                      className={style.link}
                      style={{ padding: "5%" }}
                      href="/signup"
                    >
                      {"Don't have an account? Sign Up"}
                    </Link>
                  </div>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
}
