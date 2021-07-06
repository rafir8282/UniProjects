using Microsoft.EntityFrameworkCore.Migrations;

namespace WDPAssignment2.Data.Migrations
{
    public partial class initial3 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "canIncreaseLike",
                table: "MachineLearningCompaniesFeedback",
                nullable: false,
                defaultValue: false);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "canIncreaseLike",
                table: "MachineLearningCompaniesFeedback");
        }
    }
}
